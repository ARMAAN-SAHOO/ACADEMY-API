// package com.armaan.academyapi.service.serviceImpl;

// import java.util.Random;
// import java.util.concurrent.TimeUnit;

// import org.springframework.data.redis.core.StringRedisTemplate;
// import org.springframework.stereotype.Service;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class OTPService {

//     private final StringRedisTemplate redisTemplate;

//     public String generateOTP() {
//         Random random = new Random();
//         return String.format("%06d", random.nextInt(999999));  // Returns a 6-digit OTP
//     }

    
//     public void storeOTP(String contact,String otp){

//         String otpKey="otp:"+contact;
//         redisTemplate.opsForValue().set(otpKey, otp,1,TimeUnit.MINUTES);
//         System.out.println("OTP stored for " + contact);
//     }

//       /**
//      * Validate OTP by comparing the entered OTP with the stored one
//      * @param contact The phone number/email associated with the OTP
//      * @param enteredOtp The OTP entered by the user
//      * @return true if the OTP is valid, false otherwise
//      */
//     public boolean validateOTP(String contact, String enteredOtp){

//         String otpKey = "otp:" + contact;
//         String storedOtp = redisTemplate.opsForValue().get(otpKey);

//          if (storedOtp != null && storedOtp.equals(enteredOtp)) {
//             // OTP is valid, delete it from Redis after successful validation
//             redisTemplate.delete(otpKey);
//             System.out.println("OTP validated for " + contact);
//             return true;
//         }

//         // OTP is invalid or expired
//         System.out.println("OTP validation failed for " + contact);
//         return false;
//     }

//         public boolean isOTPExpired(String contact) {
//         String otpKey = "otp:" + contact;
//         Long ttl = redisTemplate.getExpire(otpKey, TimeUnit.MILLISECONDS);  // Get TTL in milliseconds

//         // Return true if TTL is less than or equal to 0 (expired or non-existent)
//         return ttl != null && ttl <= 0;
//     }

// }
