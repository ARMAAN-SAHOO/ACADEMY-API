package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Payment;
import com.armaan.academyapi.repository.PaymentRepository;
import com.armaan.academyapi.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final RazorpayClient razorpayClient;
    private final PaymentRepository paymentRepository;


    public String createOrder(int amountInRupees,String receiptId) throws Exception{

        JSONObject orderRequest=new JSONObject();
        orderRequest.put("amount", amountInRupees * 100);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", receiptId);

        Order order=razorpayClient.orders.create(orderRequest);
        return order.toString();

    }




    @Override
    public Payment recordPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsForStudent(Long studentId) {
        return paymentRepository.findAllByEnrollmentStudentStudentId(studentId);
    }

    @Override
    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(()-> new RuntimeException("Payment not found"));
    }

    @Override
    public List<Payment> getPaymentsForEnrollment(Long enrollmentId) {
        return paymentRepository.findAllByEnrollmentEnrollmentId(enrollmentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}

