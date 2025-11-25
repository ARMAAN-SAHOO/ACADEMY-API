package com.armaan.academyapi.Notification;

public interface NotificationService {

    /**
     * Send an email notification.
     *
     * @param to      The recipient's email address.
     * @param subject The subject of the email.
     * @param message The message/content of the email.
     */
    void sendEmail(String to, String subject, String message);

    /**
     * Send an SMS notification.
     *
     * @param to      The recipient's phone number.
     * @param message The message/content of the SMS.
     */
    void sendSMS(String to, String message);

    /**
     * Send an OTP (One-Time Password) notification (could be SMS or email).
     *
     * @param to The recipient's contact (email or phone).
     */
    void sendOTP(String to);
}
