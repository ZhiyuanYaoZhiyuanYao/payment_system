package com.zhiyuan.paymentsystem.controllers;

import com.zhiyuan.paymentsystem.exceptions.NotFoundException;
import com.zhiyuan.paymentsystem.models.Payment;
import com.zhiyuan.paymentsystem.models.User;
import com.zhiyuan.paymentsystem.services.PaymentService;
import com.zhiyuan.paymentsystem.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@RestController
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;

    public PaymentController(PaymentService paymentService, UserService userService) {
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @PostMapping("/pay")
    public Payment createPayment(@RequestBody Payment payment){
        if(payment.getPayorId() == null || payment.getPayeeId() == null || payment.getAmount() <= 0){
            return null;
        }
        Integer payorId = payment.getPayorId();
        Integer payeeId = payment.getPayeeId();

        User payor, payee;

        try{
            payor = userService.findUserByUserId(payorId);
            payee = userService.findUserByUserId(payeeId);
        }catch (NotFoundException nfe){
            return null;
        }

        if(payor.getStatus() == 0 || payee.getStatus() == 0 || payee.getType().equals(payor.getType())){
            return null;
        }
        return paymentService.savePayment(payment);
    }

    @GetMapping("/payments/sender/{payorId}")
    public List<Payment> getPaymentsByPayorId(@PathVariable Integer payorId){
        try{
            userService.findUserByUserId(payorId);
        }catch (NotFoundException nfe){
            return null;
        }

        return paymentService.findPaymentByPayorId(payorId);
    }

    @GetMapping("/payments/recipient/{payeeId}")
    public List<Payment> getPaymentsByPayeeId(@PathVariable Integer payeeId){
        try{
            userService.findUserByUserId(payeeId);
        }catch (NotFoundException nfe){
            return null;
        }
        return paymentService.findPaymentByPayeeId(payeeId);
    }

    @GetMapping("/payments/confirm/{paymentId}")
    public Payment confirmPayment(@PathVariable Integer paymentId){
        try{
            paymentService.findPaymentByPaymentId(paymentId);
        }catch (NotFoundException nfe){
            return null;
        }
        Payment paymentReference = paymentService.findPaymentByPaymentId(paymentId);
        return paymentService.updatePayment(paymentReference);
    }

    @GetMapping("payments/all")
    public List<Payment> getAllPayments(){
        return paymentService.findAllPayments();
    }
}
