package com.zhiyuan.paymentsystem.models;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;

    private Integer clientId;

    private Integer managerId;

    private String contractText;

    private Integer status = 0; // 0-unsigned; 1-signed

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", clientId=" + clientId +
                ", managerId=" + managerId +
                ", contractText='" + contractText + '\'' +
                ", status=" + status +
                '}';
    }
}
