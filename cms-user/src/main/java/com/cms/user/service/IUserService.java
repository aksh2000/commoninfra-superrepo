package com.cms.user.service;

import com.cms.user.dto.ProfileDetails;
import com.cms.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface IUserService {
    Optional<User> findById(String userId);

    User addUser(User user);

    Boolean doesUserExist(String userId);

    //ProfileDetails getUserDetailsWithRating(String userId);
}
