package org.pizzashop.clients;

import lombok.RequiredArgsConstructor;
import org.pizzashop.builders.OrderFilterParams;
import org.pizzashop.dto.OrderDTO;
import org.pizzashop.dto.OrderResponseDTO;
import org.pizzashop.enums.OrderStatus;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderApiClient {
    private final RestTemplate orderRestTemplate;

    public List<OrderResponseDTO> getOrders(OrderFilterParams orderFilterParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/getOrders");

        if (orderFilterParams.getCustomerId() != null) {
            builder.queryParam("customerId", orderFilterParams.getCustomerId());
        }
        if (orderFilterParams.getStatus() != null) {
            builder.queryParam("status", orderFilterParams.getStatus().name());
        }
        if (orderFilterParams.getOrderDate() != null) {
            builder.queryParam("createdDate", orderFilterParams.getOrderDate());
        }

        String url = builder.toUriString();

        return orderRestTemplate.exchange
                (
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<OrderResponseDTO>>() {
                        }
                ).getBody();
    }

    public OrderResponseDTO getOrderById(Long orderId) {
        String url = "/getOrderById/" + orderId;
        return orderRestTemplate.exchange
                (
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<OrderResponseDTO>() {
                        }
                ).getBody();
    }

    public OrderStatus changeOrderStatus(Long orderId, OrderStatus status) {
        String url = "/changeOrderStatus/" + orderId + "?status=" + status.name();
        return orderRestTemplate.exchange
                (
                        url,
                        HttpMethod.PATCH,
                        null,
                        new ParameterizedTypeReference<OrderStatus>() {
                        }
                ).getBody();
    }

    public OrderResponseDTO changeOrderDetails(Long orderId, OrderDTO orderDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OrderDTO> requestEntity = new HttpEntity<>(orderDTO, headers);

        String url = "/changeOrderDetails/" + orderId;
        return orderRestTemplate.exchange
                (
                        url,
                        HttpMethod.PATCH,
                        requestEntity,
                        new ParameterizedTypeReference<OrderResponseDTO>() {
                        }
                ).getBody();
    }
}
