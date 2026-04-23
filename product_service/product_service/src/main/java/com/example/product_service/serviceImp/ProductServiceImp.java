package com.example.product_service.serviceImp;

import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepo;
import com.example.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public Product create(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepo.findById(id).orElseThrow(()-> new RuntimeException("Product doesn't exists"));
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public Product update(Long id, Product product) {
        Product existing = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setBrand(product.getBrand());
        existing.setCategory(product.getCategory());
        existing.setSku(product.getSku());
        existing.setImageUrl(product.getImageUrl());
        existing.setPrice(product.getPrice());
        existing.setDiscountPrice(product.getDiscountPrice());
        existing.setCostPrice(product.getCostPrice());
        existing.setCurrency(product.getCurrency());
        existing.setStockQuantity(product.getStockQuantity());
        existing.setInStock(product.getInStock());
        existing.setReorderLevel(product.getReorderLevel());
        existing.setRating(product.getRating());
        existing.setReviewCount(product.getReviewCount());
        existing.setSoldCount(product.getSoldCount());
        existing.setUpdatedAt(LocalDateTime.now());

        return productRepo.save(existing);
    }


    @Override
    public void delete(Long id) {
        Product product = productRepo.findById(id).orElseThrow(()->new RuntimeException("product doesn't exists"));
        productRepo.delete(product);
    }
}
