package com.zhiyuan.paymentsystem.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;
    private Integer senderId;
    private Integer recipientId;
    private String messageText;
    private Timestamp messageTimestamp = new Timestamp(System.currentTimeMillis());

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", senderId=" + senderId +
                ", recipientId=" + recipientId +
                ", messageText='" + messageText + '\'' +
                ", messageTimestamp=" + messageTimestamp +
                '}';
    }
}
