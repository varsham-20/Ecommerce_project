package com.example.order_service.service;

import com.example.order_service.model.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order);

    List<String> getProductsByIds(List<Long> productIds);

    List<Order> getAll();

    Order update(Long id, Order order);

    void delete(Long id);

    Order updateOrderStatus(Long orderId, String status);

    void cancelOrder(Long orderId);

    Order createOrderFromCart(String email);

}