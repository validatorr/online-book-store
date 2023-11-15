package v.hryhoryk.onlinebookstore.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import v.hryhoryk.onlinebookstore.dto.bookdto.BookDto;
import v.hryhoryk.onlinebookstore.dto.bookdto.BookDtoWithoutCategoryIds;
import v.hryhoryk.onlinebookstore.dto.bookdto.CreateBookRequestDto;
import v.hryhoryk.onlinebookstore.model.Book;
import v.hryhoryk.onlinebookstore.model.Category;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl")
public interface BookMapper {
    BookDto toDto(Book book);

    Book toBook(CreateBookRequestDto bookDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        bookDto.setCategoryIds(book.getCategories().stream()
                .map(Category::getId)
                .toList());
    }

    void updateBookFromDto(CreateBookRequestDto book, @MappingTarget Book entity);
}
