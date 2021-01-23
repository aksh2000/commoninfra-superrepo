package com.cms.user.controller;

import com.cms.user.entity.User;
import com.cms.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/cmsUser")
public class UserController {

    @Autowired
    private
    IUserService iUserService;

    @GetMapping(value = "/getUserDetails/{userId}")
    public Optional<User> findById(@PathVariable("userId") Long userId){
        return iUserService.findById(userId);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return iUserService.addUser(user);
    }

}
