package com.zhiyuan.paymentsystem.controllers;

import com.zhiyuan.paymentsystem.exceptions.NotFoundException;
import com.zhiyuan.paymentsystem.models.User;
import com.zhiyuan.paymentsystem.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/signup")
    public String getSignupPage(Model model){
        model.addAttribute("user", new User());
        return "user-signup";
    }

    @PostMapping("/user/signup")
    public String createNewUser(@ModelAttribute User user){
        if(user.getEmail() == null || user.getPassword() == null){
            return "user-signup";
        }

        try{
            userService.findUserByEmail(user.getEmail());
        }catch (NotFoundException nfe){
            userService.saveUser(user);
            return "user-login";
        }

        return "user-signup";
    }


    @GetMapping("/user/login")
    public String getLoginPage(Model model){
        model.addAttribute("user", new User());
        return "user-login";
    }

    @PostMapping("/user/login")
    public String loginUser(@ModelAttribute User user, Model model){
        if(user.getEmail() == null || user.getPassword() == null){
            return "user-login";
        }
        String email = user.getEmail();
        String password = user.getPassword();

        User userFromDB;

        try{
            userFromDB = userService.findUserByEmail(email);
        }catch (NotFoundException nfe){
            return "user-login";
        }

        if(userFromDB != null){
            if(userFromDB.getPassword().equals(password) && userFromDB.getStatus() != 0){
                model.addAttribute("user", userFromDB);
                return "user-dashboard";
            }
        }
        return "user-login";
    }

    @GetMapping("/user/update/{id}")
    public String getEditProfilePage(@PathVariable Integer id, Model model){
        User user = userService.findUserByUserId(id);
        model.addAttribute("user", user);
        return "user-detail";
    }


    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute User user){


        String userEmail = user.getEmail().toLowerCase();

        User userFromDB = userService.findUserByEmail(userEmail);


        if(userFromDB != null){
            userFromDB.setPhone(user.getPhone());
            userFromDB.setCountry(user.getCountry());
            userFromDB.setState(user.getState());
            userFromDB.setZipcode(user.getZipcode());
            if(!user.getPassword().isEmpty()){
                userFromDB.setPassword(user.getPassword());
            }
        }

        userService.saveUser(userFromDB);

        return "user-dashboard";
    }
}
