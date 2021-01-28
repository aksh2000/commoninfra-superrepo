package com.cms.user.controller;

import com.cms.user.dto.ProfileDetails;
import com.cms.user.entity.User;
import com.cms.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@RestController
@RequestMapping(value = "/cmsUser")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/getUserDetails")
    public Optional<User> findById(@RequestHeader("username") String userId){
        return iUserService.findById(userId);
    }

    @GetMapping(value = "/getUserDetailsInternal/{userId}")
    public Optional<User> findByIdInternal(@PathVariable("userId") String userId){
        return iUserService.findById(userId);
    }

    @GetMapping(value = "/getUserDetailsInternalAman/{userId}")
    public User findByIdInternalAman(@PathVariable("userId") String userId){
        return iUserService.findById(userId).get();
    }

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody User user, @RequestHeader("username") String userId){
        user.setUserId(userId);
        return iUserService.addUser(user);
    }

    @GetMapping(value="/doesUserExist")
    public Boolean doesUserExist(@RequestHeader("username") String userId){
        return iUserService.doesUserExist(userId);
    }

//    @GetMapping(value = "/getUserDetailsWithRating")
//    public ProfileDetails getUserDetailsWithRating(@RequestHeader("username") String userId){
//        //double userRating = 0;
//        return iUserService.getUserDetailsWithRating(userId);
//    }

}
