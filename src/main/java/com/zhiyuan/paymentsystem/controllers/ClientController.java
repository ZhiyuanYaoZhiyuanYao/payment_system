package com.zhiyuan.paymentsystem.controllers;

import com.zhiyuan.paymentsystem.models.User;
import com.zhiyuan.paymentsystem.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@RestController
public class ClientController {
    private final UserService userService;

    public ClientController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/clientprofile/{id}")
    public User getClientProfile(@PathVariable Integer id){
        User user = userService.findUserByUserId(id);
        if(user.getType() != 2){
            return null;
        }
        return user;
    }

    @GetMapping("/clients/all")
    public List<User> getAllClients(){
        return userService.findUserByType(2);
    }
}
