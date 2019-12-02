package com.zhiyuan.paymentsystem.services;

import com.zhiyuan.paymentsystem.exceptions.NotFoundException;
import com.zhiyuan.paymentsystem.models.Transaction;
import com.zhiyuan.paymentsystem.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        Transaction transactionReference;

        try{
            transactionReference = findTransactionByTransactionId(transaction.getTransactionId());
        }catch (NotFoundException nfe){
            return null;
        }
        transactionReference.setStatus(transaction.getStatus());
        return transactionRepository.save(transactionReference);
    }

    @Override
    public Transaction findTransactionByTransactionId(Integer transactionId) {
        Optional<Transaction> transactionOptional = transactionRepository.findTransactionByTransactionId(transactionId);
        if(!transactionOptional.isPresent()){
            log.error("Transaction with ID:{} is not found.", transactionId);
            throw new NotFoundException("Transaction with ID:" + transactionId + " is not found.");
        }
        return transactionOptional.get();
    }

    @Override
    public List<Transaction> findTransactionByClientId(Integer clientId) {
        return transactionRepository.findTransactionByClientId(clientId);
    }

    @Override
    public List<Transaction> findTransacitionBymanagerId(Integer managerId) {
        return transactionRepository.findTransactionByManagerId(managerId);
    }

    @Override
    public List<Transaction> findTransactionByContractId(Integer contractId) {
        return transactionRepository.findTransactionByContractId(contractId);
    }
}
