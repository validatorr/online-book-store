package v.hryhoryk.onlinebookstore.dto.itemdto;

public record OrderItemDto(
        Long id,
        Long bookId,
        int quantity
) {}
