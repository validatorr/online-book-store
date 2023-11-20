package v.hryhoryk.onlinebookstore.service.book;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import v.hryhoryk.onlinebookstore.dto.bookdto.BookDto;
import v.hryhoryk.onlinebookstore.dto.bookdto.BookDtoWithoutCategoryIds;
import v.hryhoryk.onlinebookstore.dto.bookdto.BookSearchParameters;
import v.hryhoryk.onlinebookstore.dto.bookdto.CreateBookRequestDto;
import v.hryhoryk.onlinebookstore.exceptions.EntityNotFoundException;
import v.hryhoryk.onlinebookstore.mapper.BookMapper;
import v.hryhoryk.onlinebookstore.model.Book;
import v.hryhoryk.onlinebookstore.model.Category;
import v.hryhoryk.onlinebookstore.repository.book.BookRepository;
import v.hryhoryk.onlinebookstore.repository.book.BookSpecificationBuilder;
import v.hryhoryk.onlinebookstore.repository.category.CategoryRepository;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public BookDtoWithoutCategoryIds createBook(CreateBookRequestDto book) {
        Book bookEntity = bookMapper.toBook(book);
        setCategories(bookEntity, book.categoryIds());
        bookRepository.save(bookEntity);
        return bookMapper.toDtoWithoutCategories(bookEntity);
    }

    @Override
    public List<BookDto> getAllWithCategories(Pageable pageable) {
        return bookRepository.getBooksWithCategories(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find book with id: " + id));
    }

    @Transactional
    @Override
    public BookDto updateById(Long id, CreateBookRequestDto book) {
        Book bookFromDb = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id: " + id));
        bookMapper.updateBookFromDto(book, bookFromDb);
        setCategories(bookFromDb, book.categoryIds());
        return bookMapper.toDto(bookRepository.save(bookFromDb));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDtoWithoutCategoryIds> search(
            BookSearchParameters searchParameters, Pageable pageable) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(searchParameters);
        return bookRepository.findAll(bookSpecification, pageable).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }

    @Override
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id) {
        return bookRepository.findBooksByCategoryId(id)
                .stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }

    private void setCategories(Book book, List<Long> categoryIds) {
        Set<Category> categories = categoryIds.stream()
                .map(categoryRepository::getReferenceById)
                .collect(Collectors.toSet());
        book.setCategories(categories);
    }
}
