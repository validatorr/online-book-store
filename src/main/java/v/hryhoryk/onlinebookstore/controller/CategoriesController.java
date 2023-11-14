package v.hryhoryk.onlinebookstore.controller;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import v.hryhoryk.onlinebookstore.dto.bookdto.BookDtoWithoutCategoryIds;
import v.hryhoryk.onlinebookstore.dto.categorydto.CategoryResponseDto;
import v.hryhoryk.onlinebookstore.dto.categorydto.CreateCategoryRequestDto;
import v.hryhoryk.onlinebookstore.service.book.BookService;
import v.hryhoryk.onlinebookstore.service.category.CategoryService;

@Tag(name = "Categories managing",
        description = "Endpoints for managing categories")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @Operation(summary = "Create new category",
            description = "Admin can add new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully created"),
            @ApiResponse(responseCode = "400",
                    description = "Bad request for creating categories, validation failed.")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CategoryResponseDto createCategory(
            @RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.createCategory(requestDto);
    }

    @Operation(summary = "Get all available categories",
            description = "User or admin can get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",
                    description = "Not found - there are no categories available")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<CategoryResponseDto> getAll(
            @PageableDefault Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @Operation(summary = "Get category by book id",
            description = "User or admin can get category by book id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",
                    description = "Not found - there are no categories available")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(
            @PathVariable @Positive Long id) {
        return categoryService.getById(id);
    }

    @Operation(summary = "Update category by id",
            description = "Admin can update category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully updated"),
            @ApiResponse(responseCode = "400",
                    description = "Bad request - validation failed")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CategoryResponseDto updateCategoryById(
            @PathVariable @Positive Long id,
            @RequestBody @Valid CreateCategoryRequestDto categoryRequest) {
        return categoryService.updateById(id, categoryRequest);
    }

    @Operation(summary = "Delete category by id",
            description = "Admin can delete category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully deleted"),
            @ApiResponse(responseCode = "404",
                    description = "Not found - category with this id is not existing")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryById(
            @PathVariable @Positive Long id) {
        categoryService.deleteById(id);
    }

    @Operation(summary = "Get books by category id",
            description = "User or admin can get all books by category id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",
                    description = "Not found - category with this id is not available")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{categoryId}/books")
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(
            @PathVariable @Positive Long categoryId) {
        return bookService.getBooksByCategoryId(categoryId);
    }
}
