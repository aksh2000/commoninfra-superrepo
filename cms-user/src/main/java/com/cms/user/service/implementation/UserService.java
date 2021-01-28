package com.cms.user.service.implementation;

import com.cms.user.dto.ProfileDetails;
import com.cms.user.entity.User;
import com.cms.user.repository.UserRepository;
import com.cms.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Optional<User> findById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean doesUserExist(String userId) {
        return userRepository.existsByUserId(userId);
    }

//    @Override
//    public ProfileDetails getUserDetailsWithRating(String userId) {
//        ProfileDetails profileDetails = new ProfileDetails();
//        User user = userRepository.findById(userId).get();
//        profileDetails.setName(user.getFirstName() + "  " + user.getLastName());
//        profileDetails.setPhoneNumber(user.getPhone());
//        double userRating;
//        userRating = restTemplate.getForObject("http://localhost:9003/cmsQuiz/getUserRating", Double.class);
//        profileDetails.setUserRating(userRating);
//        return profileDetails;
//    }
}
