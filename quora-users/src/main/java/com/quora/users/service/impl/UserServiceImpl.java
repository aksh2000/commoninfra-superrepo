package com.quora.users.service.impl;

import com.quora.users.entity.User;
import com.quora.users.repository.UserRepository;
import com.quora.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserDetails(String userEmail) {
        return userRepository.findById(userEmail).get();
    }
}
