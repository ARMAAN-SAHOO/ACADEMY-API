package com.armaan.academyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.Payment;

import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>{

}
