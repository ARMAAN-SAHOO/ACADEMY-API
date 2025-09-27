package com.armaan.academyapi.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armaan.academyapi.dto.response.OrderResponseDto;
import com.armaan.academyapi.dto.response.PaymentResponseDto;
import com.armaan.academyapi.service.PaymentGatewayService;
import com.armaan.academyapi.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentGatewayService paymentGatewayService;

    // DB-related
    @GetMapping("/{id}")
    public PaymentResponseDto getPayment(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    // Razorpay-related
    @PostMapping("/create-order")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody Map<String, Object> request) throws Exception {
        int amount = (int) request.get("amount");
        String receiptId = UUID.randomUUID().toString();
        return ResponseEntity.ok(paymentGatewayService.createOrder(amount, receiptId));
    }
}

