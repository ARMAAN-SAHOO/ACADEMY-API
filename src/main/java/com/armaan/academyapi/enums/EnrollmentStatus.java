package com.armaan.academyapi.enums;

public enum EnrollmentStatus {
    CREATED,
    ENROLLED,   // just enrolled, payment not done yet
    PAID,       // payment completed
    EXPIRED,    // payment window passed without payment
    CANCELLED   // admin or user cancelled enrollment
}

