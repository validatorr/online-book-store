package v.hryhoryk.onlinebookstore.service.order;

import java.util.List;
import org.springframework.security.core.Authentication;
import v.hryhoryk.onlinebookstore.dto.orderdto.OrderDto;
import v.hryhoryk.onlinebookstore.dto.orderdto.OrderRequestDto;
import v.hryhoryk.onlinebookstore.dto.orderdto.OrderResponseWithoutItemsDto;
import v.hryhoryk.onlinebookstore.dto.orderdto.UpdateOrderStatusRequestDto;
import v.hryhoryk.onlinebookstore.dto.orderitemdto.OrderItemDto;

public interface OrderService {
    OrderResponseWithoutItemsDto placeOrder(
            Authentication authentication, OrderRequestDto orderDto);

    List<OrderDto> getOrderHistory(
            Authentication authentication);

    OrderResponseWithoutItemsDto updateOrderStatus(
            Long id, UpdateOrderStatusRequestDto requestDto);

    List<OrderItemDto> getOrderItemsByOrderId(
            Authentication authentication, Long orderId);

    OrderItemDto getOrderItemByOrderIdAndItemId(
            Authentication authentication, Long orderId, Long itemId);
}
