package com.zhiyuan.paymentsystem.controllers;

import com.zhiyuan.paymentsystem.exceptions.NotFoundException;
import com.zhiyuan.paymentsystem.models.Message;
import com.zhiyuan.paymentsystem.models.User;
import com.zhiyuan.paymentsystem.services.MessageService;
import com.zhiyuan.paymentsystem.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@RestController
public class MessageController {
    private final UserService userService;
    private final MessageService messageService;


    public MessageController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public Message createMessage(@RequestBody Message message){
        if(message.getSenderId() == null || message.getRecipientId() == null || message.getMessageText() == null){
            return null;
        }

        User sender, recipient;
        try{
            sender = userService.findUserByUserId(message.getSenderId());
            recipient = userService.findUserByUserId(message.getRecipientId());
        }catch (NotFoundException nfe){
            return null;
        }

        if(sender.getStatus() == 0 || recipient.getStatus() == 0){
            return null;
        }

        return messageService.saveMessage(message);
    }

    @GetMapping("/message/from/{userId}")
    public List<Message> getMessageBySenderId(@PathVariable Integer userId){
        try{
            userService.findUserByUserId(userId);
        }catch (NotFoundException nfe){
            return null;
        }

        return messageService.findMessageBySenderId(userId);
    }

    @GetMapping("/message/to/{userId}")
    public List<Message> getMessageByRecipientId(@PathVariable Integer userId){
        try{
            userService.findUserByUserId(userId);
        }catch (NotFoundException nfe){
            return null;
        }

        return messageService.findMessageByRecipientId(userId);
    }

    @GetMapping("/message/all")
    public List<Message> getAllMessages(){
        return messageService.findAllMessages();
    }
}
