package com.armaan.academyapi.entity;

public enum PaymentStatus {
    CREATED,        // Order created, not paid yet
    AUTHORIZED,     // Payment authorized, funds held
    CAPTURED,       // Payment successful, funds captured
    FAILED,         // Payment failed
    REFUNDED,       // Payment refunded
    CANCELLED       // User aborted checkout
}
