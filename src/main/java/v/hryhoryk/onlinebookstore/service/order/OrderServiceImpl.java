package v.hryhoryk.onlinebookstore.service.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import v.hryhoryk.onlinebookstore.dto.itemdto.OrderItemDto;
import v.hryhoryk.onlinebookstore.dto.orderdto.OrderDto;
import v.hryhoryk.onlinebookstore.dto.orderdto.OrderRequestDto;
import v.hryhoryk.onlinebookstore.dto.orderdto.OrderResponseWithoutItemsDto;
import v.hryhoryk.onlinebookstore.dto.orderdto.UpdateOrderStatusRequestDto;
import v.hryhoryk.onlinebookstore.exceptions.EntityNotFoundException;
import v.hryhoryk.onlinebookstore.mapper.OrderItemMapper;
import v.hryhoryk.onlinebookstore.mapper.OrderMapper;
import v.hryhoryk.onlinebookstore.model.CartItem;
import v.hryhoryk.onlinebookstore.model.Order;
import v.hryhoryk.onlinebookstore.model.OrderItem;
import v.hryhoryk.onlinebookstore.model.ShoppingCart;
import v.hryhoryk.onlinebookstore.model.User;
import v.hryhoryk.onlinebookstore.repository.order.OrderRepository;
import v.hryhoryk.onlinebookstore.repository.orderitem.OrderItemRepository;
import v.hryhoryk.onlinebookstore.repository.shoppingcart.ShoppingCartRepository;
import v.hryhoryk.onlinebookstore.security.CustomUserDetailsService;
import v.hryhoryk.onlinebookstore.service.shoppingcart.ShoppingCartService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartService shoppingCartService;
    private final CustomUserDetailsService customUserDetailsService;

    @Transactional
    @Override
    public OrderResponseWithoutItemsDto placeOrder(
            Authentication authentication, OrderRequestDto orderDto) {
        User user = getUser(authentication);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId());
        Order order = initializeOrder(user, orderDto.shippingAddress(), shoppingCart);
        shoppingCartService.cleanShoppingCart(shoppingCart);
        return orderMapper.toDtoWithoutItems(orderRepository.save(order));
    }

    @Override
    public List<OrderDto> getOrderHistory(
            Authentication authentication) {
        User user = getUser(authentication);
        List<Order> userOrderList = orderRepository.findAllByUserId(user.getId());
        return userOrderList.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderResponseWithoutItemsDto updateOrderStatus(
            Long id, UpdateOrderStatusRequestDto requestDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Id " + id + " is not available for update"));
        order.setStatus(requestDto.status());
        return orderMapper.toDtoWithoutItems(orderRepository.save(order));
    }

    @Override
    public List<OrderItemDto> getOrderItemsByOrderId(
            Authentication authentication, Long orderId) {
        User user = getUser(authentication);
        Order order = getOrderByIdAndUserId(orderId, user);
        return order.getOrderItems().stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemDto getOrderItemByOrderIdAndItemId(
            Authentication authentication, Long orderId, Long itemId) {
        User user = getUser(authentication);
        Order order = getOrderByIdAndUserId(orderId, user);
        OrderItem orderItem = orderItemRepository.findByIdAndOrderId(itemId, order.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order item with this id: " + itemId));
        return orderItemMapper.toDto(orderItem);
    }

    private Order getOrderByIdAndUserId(
            Long orderId, User user) {
        return orderRepository.findByIdAndUserId(orderId, user.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order with id: " + orderId));
    }

    private User getUser(
            Authentication authentication) {
        return (User) customUserDetailsService.loadUserByUsername(authentication.getName());
    }

    private Order initializeOrder(
            User user, String shippingAddress, ShoppingCart shoppingCart) {
        Order order = new Order();
        List<OrderItem> orderItems = initializeOrderItems(order, shoppingCart.getCartItems());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.Status.PENDING);
        order.setUser(user);
        order.setShippingAddress(shippingAddress);
        order.setTotal(calculateTotalPrice(orderItems));
        order.setOrderItems(orderItems);
        return order;
    }

    private BigDecimal calculateTotalPrice(
            List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<OrderItem> initializeOrderItems(
            Order order, Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(getOrderItemFromCartItem(order))
                .collect(Collectors.toList());
    }

    private Function<CartItem, OrderItem> getOrderItemFromCartItem(
            Order order) {
        return cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setOrder(order);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getBook().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            return orderItem;
        };
    }
}
