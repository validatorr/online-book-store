package v.hryhoryk.onlinebookstore.dto.cartitemdto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartItemQuantityDto(
        @NotNull @Positive Integer quantity
) {
}
