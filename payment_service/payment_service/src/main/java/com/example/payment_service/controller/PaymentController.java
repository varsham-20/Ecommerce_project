package com.example.payment_service.controller;

import com.example.payment_service.model.Payment;
import com.example.payment_service.repository.PaymentRepo;
import com.example.payment_service.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment create(@RequestBody Payment payment){
        return paymentService.create(payment);
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable Long id){
        return paymentService.getById(id);
    }

    @GetMapping
    public List<Payment> getAll(){
        return paymentService.getAll();
    }

    @PutMapping("/{id}")
    public Payment update(@PathVariable Long id,@RequestBody Payment payment){
        System.out.println("UPDATE API HIT");
        return paymentService.update(id,payment);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        paymentService.delete(id);
        return "deleted successfully.....";
    }
}
