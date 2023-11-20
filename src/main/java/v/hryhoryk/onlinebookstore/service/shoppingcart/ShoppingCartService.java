package v.hryhoryk.onlinebookstore.service.shoppingcart;

import org.springframework.security.core.Authentication;
import v.hryhoryk.onlinebookstore.dto.cartitemdto.CartItemDto;
import v.hryhoryk.onlinebookstore.dto.cartitemdto.CartItemQuantityDto;
import v.hryhoryk.onlinebookstore.dto.cartitemdto.CartItemRequestDto;
import v.hryhoryk.onlinebookstore.dto.shoppingcartdto.ShoppingCartDto;
import v.hryhoryk.onlinebookstore.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCartDto getShoppingCartByUserId(Authentication authentication);

    CartItemDto addCartItemToShoppingCart(CartItemRequestDto cartItemRequestDto,
                                          Authentication authentication);

    void deleteCartItem(Authentication authentication, Long cartItemId);

    CartItemDto updateQuantityOfCartItem(Authentication authentication,
                                         Long carItemId, CartItemQuantityDto requestDto);

    void cleanShoppingCart(ShoppingCart shoppingCart);
}
