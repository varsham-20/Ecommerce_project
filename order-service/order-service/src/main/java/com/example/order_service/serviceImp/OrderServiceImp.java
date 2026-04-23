package com.example.order_service.serviceImp;

import com.example.order_service.client.ProductClient;
import com.example.order_service.dto.Product;
import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private final RestTemplate restTemplate;

    public void createOrder(Long productId) {

        Product product = productClient.getProduct(productId);

        System.out.println(product.getName());
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<String> getProductsByIds(List<Long> productIds) {

        List<String> products = new ArrayList<>();

        for (Long id : productIds) {

            String url = "http://localhost:8082/products/" + id;

            String product = restTemplate.getForObject(url, String.class);

            products.add(product);
        }

        return products;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Long id, Order order) {

        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        existing.setAddress(order.getAddress());
        existing.setUpdatedAt(order.getUpdatedAt());
        existing.setTotalAmount(order.getTotalAmount());
        existing.setUserEmail(order.getUserEmail());
        existing.setStatus(order.getStatus());
        existing.setPaymentStatus(order.getPaymentStatus());
        existing.setPaymentId(order.getPaymentId());

        return orderRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus("CANCELLED");

        orderRepository.save(order);
    }

    @Override
    public Order createOrderFromCart(String email) {

        Order order = new Order();
        order.setUserEmail(email);
        order.setStatus("CREATED");

        return orderRepository.save(order);
    }

    public void processPayment(Long orderId, double amount) {

        String paymentServiceUrl = "http://localhost:8084/payments";

        Map<String, Object> paymentRequest = new HashMap<>();
        paymentRequest.put("orderId", orderId);
        paymentRequest.put("amount", amount);

        restTemplate.postForEntity(paymentServiceUrl, paymentRequest, String.class);
    }

    public void sendNotification(Long orderId, String message) {

        String notificationUrl = "http://localhost:8085/api/notifications";

        Map<String, Object> notification = new HashMap<>();
        notification.put("userId", 1);
        notification.put("message", message);
        notification.put("type", "SYSTEM");

        restTemplate.postForEntity(notificationUrl, notification, String.class);
    }
}