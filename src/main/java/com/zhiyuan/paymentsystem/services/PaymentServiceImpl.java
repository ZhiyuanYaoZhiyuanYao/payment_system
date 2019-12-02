package com.zhiyuan.paymentsystem.services;

import com.zhiyuan.paymentsystem.exceptions.NotFoundException;
import com.zhiyuan.paymentsystem.models.Payment;
import com.zhiyuan.paymentsystem.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        Payment paymentReference;
        try{
            paymentReference = findPaymentByPaymentId(payment.getPaymentId());
        }catch (NotFoundException nfe){
            return null;
        }
        paymentReference.setStatus(1);
        return paymentRepository.save(paymentReference);
    }

    @Override
    public Payment findPaymentByPaymentId(Integer paymentId) {
        Optional<Payment> paymentOptional = paymentRepository.findPaymentByPaymentId(paymentId);
        if(!paymentOptional.isPresent()){
            log.error("Payment with ID:{} is not found.", paymentId);
            throw new NotFoundException("Payment with ID:" + paymentId + " is not found.");
        }
        return paymentOptional.get();
    }

    @Override
    public List<Payment> findPaymentByPayorId(Integer payorId) {
        return paymentRepository.findPaymentByPayorId(payorId);
    }

    @Override
    public List<Payment> findPaymentByPayeeId(Integer payeeId) {
        return paymentRepository.findPaymentByPayeeId(payeeId);
    }

    @Override
    public List<Payment> findPaymentByPayorIdAndPayeeId(Integer payorId, Integer payeeId) {
        return paymentRepository.findPaymentByPayorIdAndPayeeId(payorId, payeeId);
    }
}
