package com.zhiyuan.paymentsystem.services;

import com.zhiyuan.paymentsystem.models.Message;

import java.util.List;

/**
 * Created by Zhiyuan Yao
 */
public interface MessageService {
    Message saveMessage(Message message);
    List<Message> findMessageBySenderId(Integer senderId);
    List<Message> findMessageByRecipientId(Integer recipientId);
}
