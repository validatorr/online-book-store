package v.hryhoryk.onlinebookstore.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import v.hryhoryk.onlinebookstore.dto.orderdto.OrderDto;
import v.hryhoryk.onlinebookstore.dto.orderdto.OrderResponseWithoutItemsDto;
import v.hryhoryk.onlinebookstore.model.Order;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl",
        uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderDto toDto(Order order);

    OrderResponseWithoutItemsDto toDtoWithoutItems(Order order);
}
