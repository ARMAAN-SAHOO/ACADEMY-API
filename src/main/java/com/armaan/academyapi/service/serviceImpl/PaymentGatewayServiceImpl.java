package com.armaan.academyapi.service.serviceImpl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.response.OrderResponseDto;
import com.armaan.academyapi.service.PaymentGatewayService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Refund;
import com.razorpay.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    private final RazorpayClient razorpayClient;

    @Value("${razorpay.key.secret}")
    private String razorpaySecret;

    @Override
    public OrderResponseDto createOrder(int amountInRupees, String receiptId) throws Exception {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amountInRupees * 100);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", receiptId);
        orderRequest.put("payment_capture", 1); // ✅ auto capture payment

        Order order = razorpayClient.orders.create(orderRequest);

        return OrderResponseDto.builder()
                .id(order.get("id"))
                .amount(order.get("amount"))
                .currency(order.get("currency"))
                .receipt(order.get("receipt"))
                .status(order.get("status"))
                .build();
    }

    @Override
    public boolean verifySignature(String orderId, String paymentId, String signature) {
        try {
            String data = orderId + "|" + paymentId;
            return Utils.verifySignature(data, signature, razorpaySecret);
        } catch (Exception e) {
            // optional: log the failure
            return false;
        }
    }

    // @Override
    // public RefundResponseDto refund(String paymentId, int amountInRupees) throws
    // Exception {
    // JSONObject refundRequest = new JSONObject();
    // refundRequest.put("amount", amountInRupees * 100);
    // Refund refund = razorpayClient.payments.refund(paymentId, refundRequest);

    // return RefundResponseDto.builder()
    // .id(refund.get("id"))
    // .amount(refund.get("amount"))
    // .status(refund.get("status"))
    // .build();
    // }

}
