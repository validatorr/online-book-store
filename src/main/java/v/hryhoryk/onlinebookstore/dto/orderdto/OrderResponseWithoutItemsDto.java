package v.hryhoryk.onlinebookstore.dto.orderdto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import v.hryhoryk.onlinebookstore.model.Order;

public record OrderResponseWithoutItemsDto(
        Long id,
        LocalDateTime orderDate,
        BigDecimal total,
        Order.Status status,
        String shippingAddress
) {}
