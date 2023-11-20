package v.hryhoryk.onlinebookstore.service.book;

import java.util.List;
import org.springframework.data.domain.Pageable;
import v.hryhoryk.onlinebookstore.dto.bookdto.BookDto;
import v.hryhoryk.onlinebookstore.dto.bookdto.BookDtoWithoutCategoryIds;
import v.hryhoryk.onlinebookstore.dto.bookdto.BookSearchParameters;
import v.hryhoryk.onlinebookstore.dto.bookdto.CreateBookRequestDto;

public interface BookService {
    BookDtoWithoutCategoryIds createBook(CreateBookRequestDto book);

    List<BookDto> getAllWithCategories(Pageable pageable);

    BookDto getById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto book);

    void deleteById(Long id);

    List<BookDtoWithoutCategoryIds> search(
            BookSearchParameters searchParameters, Pageable pageable);

    List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id);
}
