package v.hryhoryk.onlinebookstore.dto.cartitemdto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartItemRequestDto(
        @NotNull
        @Positive
        Long bookId,
        @NotNull
        @Positive
        Integer quantity
) {
}
