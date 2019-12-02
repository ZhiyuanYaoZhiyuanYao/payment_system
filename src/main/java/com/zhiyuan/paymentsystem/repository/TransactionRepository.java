package com.zhiyuan.paymentsystem.repository;

import com.zhiyuan.paymentsystem.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zhiyuan Yao
 */
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<Transaction> findTransactionByTransactionId(Integer transanctionId);
    List<Transaction> findTransactionByClientId(Integer clientId);
    List<Transaction> findTransactionByManagerId(Integer managerId);
    List<Transaction> findTransactionByContractId(Integer contractId);
    List<Transaction> findTransactionByStatus(Integer status);
}
