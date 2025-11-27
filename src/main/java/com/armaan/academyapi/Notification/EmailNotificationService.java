// package com.armaan.academyapi.Notification;

// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.stereotype.Service;

// import com.armaan.academyapi.service.serviceImpl.OTPService;

// import jakarta.mail.MessagingException;
// import jakarta.mail.internet.MimeMessage;
// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class EmailNotificationService  implements NotificationService{

//     private final JavaMailSender mailSender;
//     private final OTPService otpService;

//     @Override
//     public void sendEmail(String to, String subject, String message) {
//         try {
//             MimeMessage mailMessage=mailSender.createMimeMessage();
//             MimeMessageHelper messageHelper=new MimeMessageHelper(mailMessage,true);

//             messageHelper.setTo(to);
//             messageHelper.setSubject(subject);
//             messageHelper.setText(message);

//             mailSender.send(mailMessage);
//             System.out.println("Email sent to "+to);
//         } catch (MessagingException e) {
//                   e.printStackTrace();
//         }
//     }

//     @Override
//     public void sendSMS(String to, String message) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'sendSMS'");
//     }

//      @Override
//     public void sendOTP(String to) {
//         String otp = otpService.generateOTP();  // Call OTPService to generate OTP
//         String subject = "Your OTP Code";
//         String body = "Your OTP is: " + otp;
//         sendEmail(to, subject, body);  // Send OTP via email
//     }

// }
