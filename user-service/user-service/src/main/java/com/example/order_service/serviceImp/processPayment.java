package com.example.order_service.serviceImp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class processPayment {

    @Autowired
    private final RestTemplate restTemplate;

    public processPayment(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void processPayment(Long orderId, double amount) {
        String paymentServiceUrl = "http://localhost:8082/payments"; // Payment Service URL

        Map<String, Object> paymentRequest = new HashMap<>();
        paymentRequest.put("orderId", orderId);
        paymentRequest.put("amount", amount);

        ResponseEntity<String> response = restTemplate.postForEntity(
                paymentServiceUrl, paymentRequest, String.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Payment successful for order: " + orderId);
        } else {
            System.out.println("Payment failed for order: " + orderId);
        }
    }

    public void sendNotification(Long orderId, String message) {
        String notificationUrl = "http://localhost:8085/api/notifications";

        Map<String, Object> notification = new HashMap<>();
        notification.put("userId", 1); // you can dynamically get from order
        notification.put("message", message);
        notification.put("type", "SYSTEM");

        restTemplate.postForEntity(notificationUrl, notification, String.class);
    }
}
