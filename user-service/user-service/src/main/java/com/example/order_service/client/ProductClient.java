package com.example.order_service.client;

import com.example.order_service.dto.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductClient {

    @GetMapping("/products/{id}")
    Product getProduct(@PathVariable Long id);
}
