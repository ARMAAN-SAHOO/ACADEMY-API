package com.armaan.academyapi.entity;

public enum EnrollmentStatus {
    ENROLLED,   // just enrolled, payment not done yet
    PAID,       // payment completed
    EXPIRED,    // payment window passed without payment
    CANCELLED   // admin or user cancelled enrollment
}

