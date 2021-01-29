package com.quora.users.service;

import com.quora.users.entity.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);

    User getUserDetails(String userEmail);

    Long switchPrivacy(String userEmail);

    List<User> findByAssociatedBusinessEmail(String associatedBusinessEmail);
}
