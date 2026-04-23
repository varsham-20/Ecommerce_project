package com.example.order_service.controller;

import com.example.order_service.model.Order;
import com.example.order_service.service.OrderService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Order create(@RequestBody Order order){
        return orderService.create(order);
    }

    @PostMapping("/products")
    public List<String> getProducts(@RequestBody List<Long> productIds){
        return orderService.getProductsByIds(productIds);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Order> getAll(){
        return orderService.getAll();
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody Order order){
        return orderService.update(id, order);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        orderService.delete(id);
        return "Order deleted successfully";
    }

//    @PostMapping("/checkout")
//    @PreAuthorize("hasRole('USER')")
//    public Order checkout(Authentication authentication){
//
//        String email = authentication.getName();
//
//        return orderService.createOrderFromCart(email);
//    }
}
