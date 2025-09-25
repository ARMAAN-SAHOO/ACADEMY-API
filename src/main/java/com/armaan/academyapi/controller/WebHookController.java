package com.armaan.academyapi.controller;

import java.io.BufferedReader;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Utils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/webhook")
public class WebHookController {

    
    @Value("${razorpay.webhook.secret}")
    private String webhookSecret;
   
    @PostMapping("/razorpay")
    public String handleRazorPayWebhook( HttpServletRequest request,
    @RequestHeader("X-Razorpay-Signature")String signature)throws Exception{

        StringBuilder body=new StringBuilder();
        try(BufferedReader reader=request.getReader()){
            String line;
            while((line=reader.readLine())!=null){
                body.append(line);
            }
        
        }
        String payload=body.toString();

        boolean isValid=Utils.verifyWebhookSignature(payload, signature, webhookSecret);

        if(!isValid){
             System.err.println("⚠️ Invalid Razorpay signature! Possible spoofed request.");
            return "Invalid signature";
        }

        JSONObject webhookPayload=new JSONObject(payload);
        String event=webhookPayload.getString("event");
        JSONObject payloadObj=webhookPayload.getJSONObject("payload");

        switch (event) {
            case "payment.captured":
                JSONObject paymentEntity=payloadObj.getJSONObject("payment").getJSONObject("enitity");
                String paymentId=paymentEntity.getString("id");
                int amount = paymentEntity.getInt("amount"); // in paise
                String currency = paymentEntity.getString("currency");
                System.out.println("✅ Payment Captured: " + paymentId +
                        " | Amount: " + (amount / 100.0) + " " + currency);
                break;
        
            case "payment.failed":
                 JSONObject failedEntity = payloadObj.getJSONObject("payment").getJSONObject("entity");
                String failedId = failedEntity.getString("id");
                System.out.println("❌ Payment Failed: " + failedId);
                break;
            default:
                 System.out.println("ℹ️ Unhandled event: " + event);
        }
        return "OK";
    }

}
