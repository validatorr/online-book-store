package v.hryhoryk.onlinebookstore.dto.orderdto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import v.hryhoryk.onlinebookstore.dto.itemdto.OrderItemDto;
import v.hryhoryk.onlinebookstore.model.Order;

public record OrderDto(
        Long id,
        Long userId,
        List<OrderItemDto> orderItems,
        LocalDateTime orderDate,
        BigDecimal total,
        Order.Status status
) { }
