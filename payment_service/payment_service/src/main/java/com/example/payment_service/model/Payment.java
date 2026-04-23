package com.example.payment_service.model;

import com.example.payment_service.enums.PaymentStatus;
import jakarta.persistence.*;
import com.example.payment_service.enums.PaymentMethodType;
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
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private Long userId;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethodType method;

    private String transactionId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
