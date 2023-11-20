package v.hryhoryk.onlinebookstore.dto.orderitemdto;

public record OrderItemDto(
        Long id,
        Long bookId,
        int quantity
) {}
