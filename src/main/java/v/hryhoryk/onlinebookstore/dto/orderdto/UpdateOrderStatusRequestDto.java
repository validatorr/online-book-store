package v.hryhoryk.onlinebookstore.dto.orderdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import v.hryhoryk.onlinebookstore.model.Order;

public record UpdateOrderStatusRequestDto(
        @NotBlank
        @Size(max = 50)
        Order.Status status
) {
}
