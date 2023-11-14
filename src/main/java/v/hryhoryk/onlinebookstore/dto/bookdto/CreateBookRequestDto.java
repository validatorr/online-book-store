package v.hryhoryk.onlinebookstore.dto.bookdto;

import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.URL;

public record CreateBookRequestDto(
        @NotNull(message = "Title can't be null")
        String title,
        @NotNull(message = "Author can't be null")
        String author,
        @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})"
                + "[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$",
                message = "Invalid ISBN format")
        String isbn,
        @PositiveOrZero(message = "Price can't be negative")
        BigDecimal price,
        @NotNull(message = "Description should contain some useful information")
        String description,
        @URL(message =
                "It's required to add image of book to increase customer's trust rate")
        String coverImage,
        @NotEmpty(message = "Make sure that book has it's category")
        List<Long> categoryIds
) {
}
