package org.pizzashop.clients;

import lombok.RequiredArgsConstructor;
import org.pizzashop.dto.ProductResponseDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryApiClient {
    private final RestTemplate categoryRestTemplate;

    public List<ProductResponseDTO> getMenu(int page, int size) {
        String url = "products?page=" + page + "&size=" + size;
        return categoryRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductResponseDTO>>() {
                }
        ).getBody();
    }
}
