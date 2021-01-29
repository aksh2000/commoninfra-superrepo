package com.quora.users.service.impl;

import com.quora.users.entity.Business;
import com.quora.users.entity.User;
import com.quora.users.repository.UserRepository;
import com.quora.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        Business business = new Business();
        business.setBusinessEmail(user.getUserEmail());
        String fullName = user.getFirstName()+" "+user.getLastName();
        business.setBusinessName(fullName);
        business.setPhotoUrl(user.getPhotoUrl());
        business.setPhotoUrl(user.getPhotoUrl());
        try {
            Business business1 = restTemplate.postForObject("http://localhost:8082/search/saveUser", business, Business.class);
        }
        catch(Exception e){
            System.out.println("Unable to save in ElasticSearch Database!!");
            System.out.println(e);
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserDetails(String userEmail) {
        return userRepository.findById(userEmail).get();
    }

    @Override
    public Integer switchPrivacy(String userEmail) {
        return userRepository.switchPrivacy(userEmail);
    }

    @Override
    public List<User> findByAssociatedBusinessEmail(String associatedBusinessEmail) {
        return userRepository.findByAssociatedBusinessEmail(associatedBusinessEmail);
    }
}
