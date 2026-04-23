package com.example.order_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private Double totalAmount;

    private String status;

    private String paymentStatus;

    private String paymentId;

    private String address;

    @ElementCollection
    private List<Long> productIds;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}