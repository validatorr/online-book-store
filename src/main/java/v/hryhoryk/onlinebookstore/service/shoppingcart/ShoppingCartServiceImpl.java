package v.hryhoryk.onlinebookstore.service.shoppingcart;

import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import v.hryhoryk.onlinebookstore.dto.cartitemdto.CartItemDto;
import v.hryhoryk.onlinebookstore.dto.cartitemdto.CartItemQuantityDto;
import v.hryhoryk.onlinebookstore.dto.cartitemdto.CartItemRequestDto;
import v.hryhoryk.onlinebookstore.dto.shoppingcartdto.ShoppingCartDto;
import v.hryhoryk.onlinebookstore.exceptions.EntityNotFoundException;
import v.hryhoryk.onlinebookstore.mapper.CartItemMapper;
import v.hryhoryk.onlinebookstore.mapper.ShoppingCartMapper;
import v.hryhoryk.onlinebookstore.model.CartItem;
import v.hryhoryk.onlinebookstore.model.ShoppingCart;
import v.hryhoryk.onlinebookstore.model.User;
import v.hryhoryk.onlinebookstore.repository.book.BookRepository;
import v.hryhoryk.onlinebookstore.repository.cartitem.CartItemRepository;
import v.hryhoryk.onlinebookstore.repository.shoppingcart.ShoppingCartRepository;
import v.hryhoryk.onlinebookstore.security.CustomUserDetailsService;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CustomUserDetailsService userDetailsService;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public ShoppingCartDto getShoppingCartByUserId(Authentication authentication) {
        User user = (User) userDetailsService.loadUserByUsername(authentication.getName());
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId());
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Transactional
    @Override
    public CartItemDto addCartItemToShoppingCart(
            CartItemRequestDto cartItemRequestDto, Authentication authentication) {
        User user = getUser(authentication);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId());
        Optional<CartItem> cartItemFromDB =
                cartItemRepository.findByShoppingCartIdAndBookId(
                        shoppingCart.getId(), cartItemRequestDto.bookId());
        if (cartItemFromDB.isPresent()) {
            CartItem item = cartItemFromDB.get();
            item.setQuantity(item.getQuantity() + cartItemRequestDto.quantity());
            return cartItemMapper.toDto(cartItemRepository.save(item));
        }
        CartItem newCartItem = new CartItem();
        newCartItem.setShoppingCart(shoppingCart);
        newCartItem.setBook(bookRepository.getReferenceById(cartItemRequestDto.bookId()));
        newCartItem.setQuantity(cartItemRequestDto.quantity());
        return cartItemMapper.toDto(cartItemRepository.save(newCartItem));
    }

    @Override
    public void deleteCartItem(Authentication authentication, Long cartItemId) {
        User user = getUser(authentication);
        CartItem cartItem = cartItemRepository.findByIdAndShoppingCartId(
                cartItemId, user.getShoppingCart().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find cart item by id " + cartItemId + " in shopping cart"));
        cartItemRepository.delete(cartItem);
    }

    @Transactional
    @Override
    public CartItemDto updateQuantityOfCartItem(
            Authentication authentication, Long cartItemId, CartItemQuantityDto requestDto) {
        User user = getUser(authentication);
        CartItem cartItem = cartItemRepository.findByIdAndShoppingCartId(
                        cartItemId, user.getShoppingCart().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find cart item by id: " + cartItemId + " in your shopping cart"));
        cartItem.setQuantity(requestDto.quantity());
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    public void cleanShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setCartItems(Collections.emptySet());
        cartItemRepository.deleteAllByShoppingCartId(shoppingCart.getId());
    }

    private User getUser(Authentication authentication) {
        return (User) userDetailsService.loadUserByUsername(authentication.getName());
    }
}
