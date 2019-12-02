package com.zhiyuan.paymentsystem.services;

import com.zhiyuan.paymentsystem.models.User;

import java.util.List;

/**
 * Created by Zhiyuan Yao
 */
public interface UserService {
    User saveUser(User user);
    User updateUser(User user);

    User findUserByUserId(Integer userId);
    List<User> findUserByFirstNameAndLastName(String firstName, String lastNanme);
    User findUserByEmail(String email);
}
