package com.zhiyuan.paymentsystem.services;

import com.zhiyuan.paymentsystem.models.Transaction;

import java.util.List;

/**
 * Created by Zhiyuan Yao
 */
public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
    Transaction updateTransaction(Transaction transaction);


    Transaction findTransactionByTransactionId(Integer transactionId);
    List<Transaction> findTransactionByClientId(Integer clientId);
    List<Transaction> findTransacitionBymanagerId(Integer managerId);
    List<Transaction> findTransactionByContractId(Integer contractId);

    List<Transaction> findAllTransactions();
}
