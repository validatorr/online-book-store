package v.hryhoryk.onlinebookstore.service;

import java.util.List;
import v.hryhoryk.onlinebookstore.dto.BookDto;
import v.hryhoryk.onlinebookstore.dto.CreateBookRequestDto;

public interface BookService {
    BookDto createBook(CreateBookRequestDto book);

    List<BookDto> getAll();

    BookDto getById(Long id);
}
