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
import v.hryhoryk.onlinebookstore.dto.bookdto.BookDto;
import v.hryhoryk.onlinebookstore.dto.bookdto.BookDtoWithoutCategoryIds;
import v.hryhoryk.onlinebookstore.dto.bookdto.BookSearchParameters;
import v.hryhoryk.onlinebookstore.dto.bookdto.CreateBookRequestDto;
import v.hryhoryk.onlinebookstore.service.book.BookService;

@Tag(name = "Book managing",
        description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Get all books",
            description = "Returns paginated and sorted view of all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",
                    description = "Not found - no books are available")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<BookDto> getAll(
            @PageableDefault Pageable pageable) {
        return bookService.getAllWithCategories(pageable);
    }

    @Operation(summary = "Get a book by ID",
            description = "Returns a book as per the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",
                    description = "Not found - book by this ID was not found")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public BookDtoWithoutCategoryIds getBookById(
            @PathVariable @Positive Long id) {
        return bookService.getById(id);
    }

    @Operation(summary = "Search books by parameters",
            description = "Returns books by certain searching parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",
                    description = "Not found - books by this searching parameters were not found")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/search")
    public List<BookDtoWithoutCategoryIds> search(
            BookSearchParameters searchParameters, Pageable pageable) {
        return bookService.search(searchParameters, pageable);
    }

    @Operation(summary = "Create a new book",
            description = "Admin can add a new book to the catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully created"),
            @ApiResponse(responseCode = "400",
                    description = "Bad request - validation failed")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDtoWithoutCategoryIds createBook(
            @RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.createBook(requestDto);
    }

    @Operation(summary = "Update book by ID",
            description = "Updates a book with the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully updated"),
            @ApiResponse(responseCode = "400",
                    description = "Bad request - request data validation failed")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public BookDto updateById(
            @PathVariable @Positive Long id, @RequestBody @Valid CreateBookRequestDto book) {
        return bookService.updateById(id, book);
    }

    @Operation(summary = "Delete book by ID",
            description = "Deletes a book with the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Successfully deleted"),
            @ApiResponse(responseCode = "404",
                    description = "Not found - book by this ID was not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable @Positive Long id) {
        bookService.deleteById(id);
    }
}
