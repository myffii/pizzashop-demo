package org.pizzashop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pizzashop.dto.OrderDTO;
import org.pizzashop.dto.OrderResponseDTO;
import org.pizzashop.models.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(OrderDTO orderDTO);

    @Mapping(target = "products", ignore = true)
    OrderResponseDTO toOrderResponseDTO(Order order);
}
