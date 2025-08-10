package org.pizzashop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {
    @Value("${api.product.url}")
    private String productApiUrl;

    @Bean
    public RestTemplate productRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        RestTemplate orderTemplate = new RestTemplate(factory);
        orderTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(productApiUrl));
        return orderTemplate;
    }
}
