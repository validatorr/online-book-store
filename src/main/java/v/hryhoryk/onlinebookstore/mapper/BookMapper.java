package v.hryhoryk.onlinebookstore.mapper;

import org.mapstruct.Mapper;
import v.hryhoryk.onlinebookstore.config.MapperConfig;
import v.hryhoryk.onlinebookstore.dto.BookDto;
import v.hryhoryk.onlinebookstore.dto.CreateBookRequestDto;
import v.hryhoryk.onlinebookstore.model.Book;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
