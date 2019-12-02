package com.zhiyuan.paymentsystem.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String firstName;
    private String lastName;
    private String phone;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String email;
    private String password;

    private Timestamp updateTimestamp = new Timestamp(System.currentTimeMillis());

    private Integer type = 2; // 0-admin; 1-manager; 2-client
    private Integer status = 1; // 0-disabled, 1-active
}
