package org.pizzashop.clients;

import lombok.RequiredArgsConstructor;
import org.pizzashop.dto.ProductResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProductApiClient {
    private final RestTemplate productRestTemplate;

    public ProductResponseDTO getProduct(Long productId) {
        return productRestTemplate.getForObject("products/" + productId, ProductResponseDTO.class);
    }
}
