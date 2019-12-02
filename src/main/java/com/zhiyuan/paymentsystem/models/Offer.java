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
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer offerId;
    private Integer clientId;
    private Integer managerId;
    private String offerText;
    private Integer status = 0; // 0-pending, 1-approved, 2-rejected

    @Override
    public String toString() {
        return "Offer{" +
                "offerId=" + offerId +
                ", cliendId=" + clientId +
                ", managerId=" + managerId +
                ", offerText='" + offerText + '\'' +
                ", status=" + status +
                '}';
    }
}
