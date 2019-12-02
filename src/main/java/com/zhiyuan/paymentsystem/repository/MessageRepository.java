package com.zhiyuan.paymentsystem.repository;

import com.zhiyuan.paymentsystem.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zhiyuan Yao
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Optional<Message> findMessageByMessageId(Integer messageId);

    List<Message> findMessageBySenderId(Integer senderId);


    List<Message> findMessageByRecipientId(Integer recipientId);


    List<Message> findMessageBySenderIdAndRecipientId(Integer senderId, Integer recipientId);

    void deleteMessageByMessageId(Integer messageId);
}
