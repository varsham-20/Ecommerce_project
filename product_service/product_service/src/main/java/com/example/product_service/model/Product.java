package com.example.product_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String brand;
    private String category;
    private String sku;
    private String imageUrl;

    private Double price;
    private Double discountPrice;
    private Double costPrice;
    private String currency;

    private Integer stockQuantity;
    private Boolean inStock;
    private Integer reorderLevel;

    private Double rating;
    private Integer reviewCount;
    private Integer soldCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
