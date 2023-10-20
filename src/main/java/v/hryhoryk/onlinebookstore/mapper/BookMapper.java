package v.hryhoryk.onlinebookstore.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import v.hryhoryk.onlinebookstore.dto.BookDto;
import v.hryhoryk.onlinebookstore.dto.CreateBookRequestDto;
import v.hryhoryk.onlinebookstore.model.Book;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl")
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
