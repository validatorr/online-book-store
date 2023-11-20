package v.hryhoryk.onlinebookstore.dto.shoppingcartdto;

import java.util.Set;
import v.hryhoryk.onlinebookstore.dto.cartitemdto.CartItemDto;

public record ShoppingCartDto(
        Long id,
        Long userId,
        Set<CartItemDto> cartItems
) {
}
