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
            Number amountNumber = (Number) request.get("amount");   // handles Integer/Double
            int amountInRupees = amountNumber.intValue();          // safe conversion
            String receiptId = UUID.randomUUID().toString();

            OrderResponseDto order=paymentGatewayService.createOrder(amountInRupees, receiptId);

                System.out.println("DEBUG - createOrder request amountInRupees=" + amountInRupees);
                 System.out.println("DEBUG - createOrder returning order.amount (paise) = " + order.getAmount());
                System.out.println("DEBUG - full order dto = " + order);

        return ResponseEntity.ok(order);
    }
}

