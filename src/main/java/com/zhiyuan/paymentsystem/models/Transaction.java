package com.zhiyuan.paymentsystem.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    private Integer clientId;
    private Integer managerId;
    private Integer contractId;
    private Integer status; // 0-ongoing, 1-successful, 2-aborted

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", clientId=" + clientId +
                ", managerId=" + managerId +
                ", contractId=" + contractId +
                ", status=" + status +
                '}';
    }
}
