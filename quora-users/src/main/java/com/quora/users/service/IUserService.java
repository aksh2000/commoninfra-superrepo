package com.quora.users.service;

import com.quora.users.entity.User;

public interface IUserService {
    User addUser(User user);

    User getUserDetails(String userEmail);

    Long switchPrivacy(String userEmail);
}
