package com.example.order_service.serviceImp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductClientService {

    @Autowired
    private final RestTemplate restTemplate;

    public ProductClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getProductById(Long productId) {
        String url = "http://localhost:8082/products/" + productId; // Product Service URL
        return restTemplate.getForObject(url, String.class);
    }
}

