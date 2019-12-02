package com.zhiyuan.paymentsystem.services;

import com.zhiyuan.paymentsystem.models.Payment;

import java.util.List;

/**
 * Created by Zhiyuan Yao
 */
public interface PaymentService {
    Payment savePayment(Payment payment);
    Payment updatePayment(Payment payment);

    Payment findPaymentByPaymentId(Integer paymentId);
    List<Payment> findPaymentByPayorId(Integer payorId);
    List<Payment> findPaymentByPayeeId(Integer payeeId);
    List<Payment> findPaymentByPayorIdAndPayeeId(Integer payorId, Integer payeeId);
}
