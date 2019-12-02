package com.zhiyuan.paymentsystem.services;

import com.sun.tools.corba.se.idl.constExpr.Not;
import com.zhiyuan.paymentsystem.exceptions.NotFoundException;
import com.zhiyuan.paymentsystem.models.User;
import com.zhiyuan.paymentsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User userReference;
        try{
            userReference = findUserByUserId(user.getUserId());
        }catch (NotFoundException nfe){
            return null;
        }

        userReference.setPhone(user.getPhone());
        userReference.setCountry(user.getCountry());
        userReference.setState(user.getState());
        userReference.setCity(user.getCity());
        userReference.setZipcode(user.getZipcode());
        userReference.setPassword(user.getPassword());
        userReference.setStatus(user.getStatus());
        return userRepository.save(userReference);
    }

    @Override
    public User findUserByUserId(Integer userId) {
        Optional<User> userOptional = userRepository.findUserByUserId(userId);
        if(!userOptional.isPresent()){
            log.error("User with ID:{} is not found. [UserServiceImpl.findUserByUserId()]", userId);
            throw new NotFoundException("User with ID:" + userId + " is not found.");
        }
        return userOptional.get();
    }

    @Override
    public List<User> findUserByFirstNameAndLastName(String firstName, String lastNanme) {
        return userRepository.findUserByFirstNameAndLastName(firstName, lastNanme);
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if(!userOptional.isPresent()){
            log.error("User with email:{} is not found", email);
            throw new NotFoundException("User with email:" + email + " is not found.");
        }
        return userOptional.get();
    }
}
