package v.hryhoryk.onlinebookstore.dto.orderdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OrderRequestDto(
        @NotBlank
        @Size(max = 200)
        String shippingAddress
) {
}
