package com.example.payment_service.serviceImp;

import com.example.payment_service.model.Payment;
import com.example.payment_service.repository.PaymentRepo;
import com.example.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {

    private final PaymentRepo repo;

    @Override
    public Payment create(Payment payment) {
        return repo.save(payment);
    }

    @Override
    public Payment getById(Long id) {
        return repo.findById(id).orElseThrow(()-> new RuntimeException("Id didn't find"));
    }

    @Override
    public List<Payment> getAll() {
        return repo.findAll();
    }

    @Override
    public Payment update(Long id, Payment payment) {

        Payment payment1 = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if(payment.getOrderId()!=null)
            payment1.setOrderId(payment.getOrderId());

        if(payment.getUserId()!=null)
            payment1.setUserId(payment.getUserId());

        if(payment.getAmount()!=null)
            payment1.setAmount(payment.getAmount());

        if(payment.getStatus()!=null)
            payment1.setStatus(payment.getStatus());

        if(payment.getMethod()!=null)
            payment1.setMethod(payment.getMethod());

        if(payment.getTransactionId()!=null)
            payment1.setTransactionId(payment.getTransactionId());

        payment1.setUpdatedAt(LocalDateTime.now());

        return repo.save(payment1);
    }

    @Override
    public void delete(Long id) {
        Payment payment = repo.findById(id).orElseThrow(()->new RuntimeException("id didn't find"));
        repo.delete(payment);
    }
}
