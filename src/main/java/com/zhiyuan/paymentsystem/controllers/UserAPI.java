package com.zhiyuan.paymentsystem.controllers;

import com.zhiyuan.paymentsystem.exceptions.NotFoundException;
import com.zhiyuan.paymentsystem.models.User;
import com.zhiyuan.paymentsystem.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@RestController
public class UserAPI{
    private final UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user/signup")
    public User createNewUser(@RequestBody User user){
        if(user.getEmail() == null || user.getPassword() == null){
            return null;
        }

        try{
            userService.findUserByEmail(user.getEmail());
        }catch (NotFoundException nfe){
            return userService.saveUser(user);
        }

        return null;
    }

    @PostMapping("api/user/login")
    public User loginUser(@RequestBody User user){
        if(user.getEmail() == null || user.getPassword() == null){
            return null;
        }
        String email = user.getEmail();
        String password = user.getPassword();

        User userFromDB;

        try{
            userFromDB = userService.findUserByEmail(email);
        }catch (NotFoundException nfe){
            return null;
        }

        if(userFromDB != null){
            if(userFromDB.getPassword().equals(password) && userFromDB.getStatus() != 0){

                return userFromDB;
            }
        }
        return null;
    }


    @PostMapping("/api/user/update")
    public User updateUser(@RequestBody User user){
        if(user.getUserId() == null){
            return null;
        }

        String userEmail = user.getEmail().toLowerCase();

        User userFromDB;

        try{
            userFromDB = userService.findUserByEmail(userEmail);
        } catch (NotFoundException nfe){
            return null;
        }

        if(userFromDB != null){
            userFromDB.setPhone(user.getPhone());
            userFromDB.setCountry(user.getCountry());
            userFromDB.setState(user.getState());
            userFromDB.setZipcode(user.getZipcode());
            if(!user.getPassword().isEmpty()){
                userFromDB.setPassword(user.getPassword());
            }
        }

        return userService.saveUser(userFromDB);
    }

    @GetMapping("/api/user/{userId}")
    public User getUserProfile(@PathVariable Integer userId){
        User userReference;
        try{
            userReference = userService.findUserByUserId(userId);
        }catch (NotFoundException nfe){
            return null;
        }

        return userReference;
    }


    @PostMapping("/api/user/disable/{userId}")
    public User suspendUser(@PathVariable Integer userId){
        User userReference;

        try{
            userReference = userService.findUserByUserId(userId);
        }catch (NotFoundException nfe){
            return null;
        }

        userReference.setStatus(0);
        return userService.updateUser(userReference);
    }

}
