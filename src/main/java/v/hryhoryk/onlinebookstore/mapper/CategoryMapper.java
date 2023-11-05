package v.hryhoryk.onlinebookstore.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import v.hryhoryk.onlinebookstore.dto.categorydto.CategoryResponseDto;
import v.hryhoryk.onlinebookstore.dto.categorydto.CreateCategoryRequestDto;
import v.hryhoryk.onlinebookstore.model.Category;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl")
public interface CategoryMapper {
    CategoryResponseDto toDto(Category category);

    Category toCategory(CreateCategoryRequestDto categoryDto);

    void updateCategory(CreateCategoryRequestDto categoryDto, @MappingTarget Category category);
}
