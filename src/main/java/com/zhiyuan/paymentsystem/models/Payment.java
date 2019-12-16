package com.zhiyuan.paymentsystem.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    private Integer payorId;
    private Integer payeeId;
    private Double amount;
    private Timestamp paymentTimestamp = new Timestamp(System.currentTimeMillis());
    private Integer status = 0; // 0-pending, 1-finished

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", payorId=" + payorId +
                ", payeeId=" + payeeId +
                ", paymentTimestamp=" + paymentTimestamp +
                ", status=" + status +
                '}';
    }
}
