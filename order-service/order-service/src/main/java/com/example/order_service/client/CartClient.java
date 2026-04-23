//package com.example.order_service.client;
//
//import com.example.order_service.dto.CartItem;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@FeignClient(name = "cart-service", url = "http://localhost:8082")
//public interface CartClient {
//
//    @GetMapping("/cart/{email}")
//    List<CartItem> getCartItems(@PathVariable String email);
//
//    @DeleteMapping("/cart/clear/{email}")
//    void clearCart(@PathVariable String email);
//}