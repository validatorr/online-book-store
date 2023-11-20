package v.hryhoryk.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import v.hryhoryk.onlinebookstore.dto.cartitemdto.CartItemDto;
import v.hryhoryk.onlinebookstore.dto.cartitemdto.CartItemQuantityDto;
import v.hryhoryk.onlinebookstore.dto.cartitemdto.CartItemRequestDto;
import v.hryhoryk.onlinebookstore.dto.shoppingcartdto.ShoppingCartDto;
import v.hryhoryk.onlinebookstore.service.shoppingcart.ShoppingCartService;

@Tag(name = "Shopping cart managing",
        description = "Endpoints for manipulating cart items")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Adds book to shopping cart",
            description = "User can add new book to his shopping cart")
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public CartItemDto addBookToShoppingCart(Authentication authentication,
                                             @RequestBody @Valid CartItemRequestDto requestDto) {
        return shoppingCartService.addCartItemToShoppingCart(requestDto, authentication);
    }

    @Operation(summary = "Get shopping cart",
            description = "User can retrieve information about shopping cart")
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ShoppingCartDto retrieveShoppingCart(Authentication authentication) {
        return shoppingCartService.getShoppingCartByUserId(authentication);
    }

    @Operation(summary = "Delete book",
            description = "User can delete book from shopping cart")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/cart-items/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookFromShoppingCart(Authentication authentication,
                                           @Positive @PathVariable Long cartItemId) {
        shoppingCartService.deleteCartItem(authentication, cartItemId);
    }

    @Operation(summary = "Update quantity of books",
            description = "User can change quantity of books")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/cart-items/{cartItemId}")
    public CartItemDto updateQuantityInShoppingCart(
            Authentication authentication,
            @Positive @PathVariable Long cartItemId,
            @RequestBody @Valid CartItemQuantityDto requestDto) {
        return shoppingCartService.updateQuantityOfCartItem(authentication, cartItemId, requestDto);
    }
}
