package com.zhiyuan.paymentsystem.services;

import com.zhiyuan.paymentsystem.models.Message;
import com.zhiyuan.paymentsystem.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> findMessageBySenderId(Integer senderId) {
        return messageRepository.findMessageBySenderId(senderId);
    }

    @Override
    public List<Message> findMessageByRecipientId(Integer recipientId) {
        return messageRepository.findMessageByRecipientId(recipientId);
    }
}
