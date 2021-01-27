package com.cms.user.controller;

import com.cms.user.entity.User;
import com.cms.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@CrossOrigin
@RestController
@RequestMapping(value = "/cmsUser")
public class UserController {

    @Autowired
    private
    IUserService iUserService;

    @GetMapping(value = "/getUserDetails")
    public Optional<User> findById(@RequestHeader("username") String userId){
        return iUserService.findById(userId);
    }

    @GetMapping(value = "/getUserDetailsInternal/{userId}")
    public Optional<User> findByIdInternal(@PathVariable("userId") String userId){
        return iUserService.findById(userId);
    }

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody User user, @RequestHeader("username") String userId){
        user.setUserId(userId);
        return iUserService.addUser(user);
    }

}
