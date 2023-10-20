package v.hryhoryk.onlinebookstore.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import v.hryhoryk.onlinebookstore.dto.BookDto;
import v.hryhoryk.onlinebookstore.dto.CreateBookRequestDto;
import v.hryhoryk.onlinebookstore.exceptions.EntityNotFoundException;
import v.hryhoryk.onlinebookstore.mapper.BookMapper;
import v.hryhoryk.onlinebookstore.model.Book;
import v.hryhoryk.onlinebookstore.repository.BookRepository;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto createBook(CreateBookRequestDto book) {
        Book bookModel = bookMapper.toModel(book);
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
}
