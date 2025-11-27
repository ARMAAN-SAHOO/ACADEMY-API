// package com.armaan.academyapi.Notification;

// import org.springframework.stereotype.Service;

// import com.armaan.academyapi.service.serviceImpl.OTPService;

// import com.twilio.rest.api.v2010.account.Message;
// import com.twilio.type.PhoneNumber;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class SMSNotificationService implements NotificationService{

//     private final OTPService otpService;

//     private final String ACCOUNT_SID = "OR506be9f6a6328955385d38cdad69ee84";
//     private final String AUTH_TOKEN = "your_auth_token";
//     private final String FROM_PHONE = "your_twilio_phone_number";

//         @Override
//     public void sendEmail(String to, String subject, String message) {
//         // Leave empty, since this service only handles SMS
//     }

//     @Override
//     public void sendSMS(String to, String message) {
//         try {
//             Message sms = Message.creator(
//                     new PhoneNumber(to),    // Recipient phone number
//                     new PhoneNumber(FROM_PHONE),  // Twilio phone number
//                     message)  // SMS message
//                 .create();
//             System.out.println("SMS sent to " + to);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     @Override
//     public void sendOTP(String to) {
//         String otp = otpService.generateOTP();  // Generate OTP
//         otpService.storeOTP(to, otp);  // Store OTP in Redis (5-minute expiration)

//         String message = "Your OTP is: " + otp;
//         sendSMS(to, message);  // Send OTP via SMS
//     }

// }
