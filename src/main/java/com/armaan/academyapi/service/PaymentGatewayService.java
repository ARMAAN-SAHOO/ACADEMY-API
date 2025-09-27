package com.armaan.academyapi.service;

import com.armaan.academyapi.dto.response.OrderResponseDto;

public interface PaymentGatewayService {
    OrderResponseDto createOrder(int amountInRupees, String receiptId) throws Exception;
    boolean verifySignature(String orderId, String paymentId, String signature);
   // void refund(String paymentId, int amountInRupees) throws Exception;
}