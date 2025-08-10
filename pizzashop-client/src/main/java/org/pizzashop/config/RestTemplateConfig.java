package org.pizzashop.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {
    @Value("${api.catalog.url}")
    private String categoryApiUrl;

    @Value("${api.order.url}")
    private String orderApiUrl;

    @Bean
    @Qualifier("categoryRestTemplate")
    public RestTemplate categoryRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        RestTemplate categoryTemplate = new RestTemplate(factory);
        categoryTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(categoryApiUrl));
        return categoryTemplate;
    }

    @Bean
    @Qualifier("orderRestTemplate")
    public RestTemplate orderRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        RestTemplate orderTemplate = new RestTemplate(factory);
        orderTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(orderApiUrl));
        return orderTemplate;
    }

}