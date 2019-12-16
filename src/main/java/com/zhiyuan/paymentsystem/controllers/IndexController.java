package com.zhiyuan.paymentsystem.controllers;

import com.zhiyuan.paymentsystem.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Controller
public class IndexController {
    @GetMapping(value = {"", "/"})
    public String indexPage(){
        return "redirect:/user/login";
    }
}
