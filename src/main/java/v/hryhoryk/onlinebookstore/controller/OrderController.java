package v.hryhoryk.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import v.hryhoryk.onlinebookstore.dto.itemdto.OrderItemDto;
import v.hryhoryk.onlinebookstore.dto.orderdto.OrderDto;
import v.hryhoryk.onlinebookstore.dto.orderdto.OrderRequestDto;
import v.hryhoryk.onlinebookstore.dto.orderdto.OrderResponseWithoutItemsDto;
import v.hryhoryk.onlinebookstore.dto.orderdto.UpdateOrderStatusRequestDto;
import v.hryhoryk.onlinebookstore.service.order.OrderService;

@Tag(name = "Orders managing",
        description = "Endpoints for managing orders and order items")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Place an order",
            description = "Allows user to place an order")
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public OrderResponseWithoutItemsDto placeOrder(
            Authentication authentication, @RequestBody @Valid OrderRequestDto requestDto) {
        return orderService.placeOrder(authentication, requestDto);
    }

    @Operation(summary = "Get order history",
            description = "Allows user to get its order history")
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<OrderDto> getOrderHistory(Authentication authentication) {
        return orderService.getOrderHistory(authentication);
    }

    @Operation(summary = "Update order status",
            description = "Allows user to update its order status")
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponseWithoutItemsDto updateOrderStatus(
            @Positive @PathVariable Long id, @RequestBody UpdateOrderStatusRequestDto requestDto) {
        return orderService.updateOrderStatus(id, requestDto);
    }

    @Operation(summary = "Get order items by order id",
            description = "Allows user to get order items by order id")
    @GetMapping("{orderId}/items")
    @PreAuthorize("hasRole('USER')")
    public List<OrderItemDto> getOrderItemsByOrderId(
            Authentication authentication, @PathVariable @Positive Long orderId) {
        return orderService.getOrderItemsByOrderId(authentication, orderId);
    }

    @Operation(summary = "Get single order item",
            description = "Allows user get single order item by order id and item id")
    @GetMapping("{orderId}/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    public OrderItemDto getOrderItemByIdAndOrderId(
            Authentication authentication, @PathVariable @Positive Long orderId,
            @PathVariable @Positive Long itemId) {
        return orderService.getOrderItemByOrderIdAndItemId(authentication, orderId, itemId);
    }
}
