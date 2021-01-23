package com.cms.user.service;

import com.cms.user.entity.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findById(Long userId);

    User addUser(User user);
}
