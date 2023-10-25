package v.hryhoryk.onlinebookstore.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import v.hryhoryk.onlinebookstore.dto.BookDto;
import v.hryhoryk.onlinebookstore.dto.BookSearchParameters;
import v.hryhoryk.onlinebookstore.dto.CreateBookRequestDto;
import v.hryhoryk.onlinebookstore.exceptions.EntityNotFoundException;
import v.hryhoryk.onlinebookstore.mapper.BookMapper;
import v.hryhoryk.onlinebookstore.model.Book;
import v.hryhoryk.onlinebookstore.repository.book.BookRepository;
import v.hryhoryk.onlinebookstore.repository.book.BookSpecificationBuilder;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto createBook(CreateBookRequestDto book) {
        Book bookModel = bookMapper.toBook(book);
        return bookMapper.toDto(bookRepository.save(bookModel));
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
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

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto book) {
        Book bookFromDb = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id: " + id));
        bookMapper.updateBookFromDto(book, bookFromDb);
        return bookMapper.toDto(bookRepository.save(bookFromDb));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> search(BookSearchParameters searchParameters) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(searchParameters);
        return bookRepository.findAll(bookSpecification).stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
