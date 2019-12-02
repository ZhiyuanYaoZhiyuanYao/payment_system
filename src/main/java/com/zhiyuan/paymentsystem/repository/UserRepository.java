package com.zhiyuan.paymentsystem.repository;

import com.zhiyuan.paymentsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zhiyuan Yao
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUserId(Integer userId);
    List<User> findUserByFirstNameAndLastName(String firstName, String lastName);
    Optional<User> findUserByEmail(String userEmail);

}
