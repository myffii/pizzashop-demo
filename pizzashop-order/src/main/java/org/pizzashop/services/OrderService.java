package org.pizzashop.services;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.pizzashop.clients.ProductApiClient;
import org.pizzashop.dto.OrderDTO;
import org.pizzashop.dto.OrderResponseDTO;
import org.pizzashop.dto.ProductResponseDTO;
import org.pizzashop.enums.OrderStatus;
import org.pizzashop.exceptions.OrderNotFoundException;
import org.pizzashop.mappers.OrderMapper;
import org.pizzashop.models.Order;
import org.pizzashop.repositories.OrderRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductApiClient productApiClient;

    @Transactional
    public Long createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toOrder(orderDTO);
        order.setOrderDate(Instant.now());
        order.setUpdatedAt(Instant.now());
        order.setStatus(OrderStatus.PENDING);

        order.setTotal(calculateTotal(order));

        return orderRepository.save(order).getId();
    }

    public OrderResponseDTO getOrderById(Long orderId) {
        OrderResponseDTO orderResponseDTO = convertToOrderResponseDTO(orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new));
        return orderResponseDTO;
    }

    public List<OrderResponseDTO> getOrders(Long customerId, OrderStatus status, LocalDate orderDate) {
        Specification<Order> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (customerId != null) {
                predicates.add(criteriaBuilder.equal(root.get("customerId"), customerId));
            }

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            if (orderDate != null) {
                Instant startOfDay = orderDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
                Instant endOfDay = orderDate.atTime(23, 59, 59, 999999999)
                        .atZone(ZoneId.systemDefault())
                        .toInstant();
                predicates.add(criteriaBuilder.between(root.get("orderDate"), startOfDay, endOfDay));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        List<OrderResponseDTO> orderResponsesDTO = convertToOrderResponseDTOList(
                orderRepository.findAll(specification)
        );

        return orderResponsesDTO;
    }

    @Transactional
    public OrderStatus changeOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.setStatus(status);
        order.setUpdatedAt(Instant.now());
        return orderRepository.save(order).getStatus();
    }

    @Transactional
    public OrderResponseDTO changeOrderDetails(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        order.setProducts(orderDTO.getProducts());

        order.setTotal(calculateTotal(order));

        order.setUpdatedAt(Instant.now());
        OrderResponseDTO orderResponseDTO = convertToOrderResponseDTO(orderRepository.save(order));
        return orderResponseDTO;
    }

    public Double calculateTotal(Order order) {

        var productResponses = new ArrayList<ProductResponseDTO>();

        Map<Long, Integer> orderProducts = order.getProducts();

        orderProducts.forEach((k, v) -> productResponses.add(productApiClient.getProduct(k)));

        Double total = productResponses.stream().mapToDouble(response ->
                response.getPrice() * orderProducts.get(response.getId())).sum();

        return total;
    }

    public OrderResponseDTO convertToOrderResponseDTO(Order order) {
        OrderResponseDTO orderResponseDTO = orderMapper.toOrderResponseDTO(order);

        Map<String, Integer> responseProducts = new HashMap<>();

        order.getProducts()
                .forEach((k, v) ->
                        responseProducts.put(productApiClient.getProduct(k).getName(), v)
                );

        orderResponseDTO.setProducts(responseProducts);

        return orderResponseDTO;
    }

    public List<OrderResponseDTO> convertToOrderResponseDTOList(List<Order> orders) {
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();

        orders.forEach(order -> orderResponseDTOList.add(convertToOrderResponseDTO(order)));

        return orderResponseDTOList;
    }
}
