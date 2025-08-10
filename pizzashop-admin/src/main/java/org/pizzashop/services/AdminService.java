package org.pizzashop.services;

import lombok.RequiredArgsConstructor;
import org.pizzashop.builders.OrderFilterParams;
import org.pizzashop.clients.OrderApiClient;
import org.pizzashop.dto.OrderDTO;
import org.pizzashop.dto.OrderResponseDTO;
import org.pizzashop.enums.OrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {
    private final OrderApiClient orderApiClient;

    public List<OrderResponseDTO> getOrders(Long customerId, OrderStatus status, LocalDate orderDate) {
        OrderFilterParams orderFilterParams = OrderFilterParams.builder()
                .customerId(customerId)
                .status(status)
                .orderDate(orderDate)
                .build();

        return orderApiClient.getOrders(orderFilterParams);
    }

    public OrderResponseDTO getOrderById(Long orderId) {
        return orderApiClient.getOrderById(orderId);
    }

    @Transactional
    public OrderStatus changeOrderStatus(Long orderId, OrderStatus status) {
        return orderApiClient.changeOrderStatus(orderId, status);
    }

    @Transactional
    public OrderResponseDTO changeOrderDetails(Long orderId, OrderDTO orderDTO) {
        return orderApiClient.changeOrderDetails(orderId, orderDTO);
    }
}
