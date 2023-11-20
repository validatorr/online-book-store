package v.hryhoryk.onlinebookstore.dto.cartitemdto;

public record CartItemDto(
        Long id,
        Long bookId,
        String bookTitle,
        int quantity
) {
}
