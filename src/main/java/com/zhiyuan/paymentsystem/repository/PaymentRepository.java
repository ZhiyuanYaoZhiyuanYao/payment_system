package com.zhiyuan.paymentsystem.repository;

import com.zhiyuan.paymentsystem.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zhiyuan Yao
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // find
    Optional<Payment> findPaymentByPaymentId(Integer paymentId);
    List<Payment> findPaymentByPayorId(Integer payorId);
    List<Payment> findPaymentByPayeeId(Integer payeeId);
    List<Payment> findPaymentByPayorIdAndPayeeId(Integer payorId, Integer payeeId);
    List<Payment> findPaymentByPayorIdAndStatus(Integer payorId, Integer status);
    List<Payment> findPaymentByPayeeIdAndStatus(Integer payeeId, Integer status);

    // delete
}
