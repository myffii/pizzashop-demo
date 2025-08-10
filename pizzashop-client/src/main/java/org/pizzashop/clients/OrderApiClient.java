package org.pizzashop.clients;

import lombok.RequiredArgsConstructor;
import org.pizzashop.dto.OrderDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OrderApiClient {
    private final RestTemplate orderRestTemplate;

    public Long createOrder(OrderDTO orderDTO) {
        return orderRestTemplate.postForObject("orders/create", orderDTO, Long.class);
    }
}
