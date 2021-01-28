package com.quora.users.controller;

import com.quora.users.entity.Business;
import com.quora.users.entity.Engagement;
import com.quora.users.entity.User;
import com.quora.users.service.IBusinessService;
import com.quora.users.service.IEngagementService;
import com.quora.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/quoraUsers")
public class UsersController {

    @Autowired
    IUserService iUserService;

    @Autowired
    IBusinessService iBusinessService;

    @Autowired
    IEngagementService iEngagementService;

    @PostMapping("/addUser")
    User addUser(@RequestBody User user, @RequestHeader("username") String userEmail){
        user.setUserEmail(userEmail);
        return iUserService.addUser(user);
    }

    @GetMapping("/getUserDetails")
    User getUserDetails(@RequestHeader("username")  String userEmail){
        return iUserService.getUserDetails(userEmail);
    }

    @PostMapping("/addBusiness")
    Business addBusiness(@RequestBody Business business, @RequestHeader("username") String businessEmail){
        business.setBusinessEmail(businessEmail);
        return iBusinessService.addBusiness(business);
    }

    @GetMapping("/getBusinessDetails")
    Business getBusinessDetails(@RequestHeader("username") String businessEmail){
        return iBusinessService.getBusinessDetails(businessEmail);
    }

    @PostMapping("/addEngagement")
    Engagement addEngagement(@RequestBody Engagement engagement, @RequestHeader("username") String secondaryEmail){
        engagement.setSecondaryEmail(secondaryEmail);
        User userDetail = iUserService.getUserDetails(engagement.getUserBusinessEmail());
        if(userDetail == null){
            engagement.setApproved(true);
        }else engagement.setApproved(!userDetail.isPrivate());

        return iEngagementService.addEngagement(engagement);
    }

    @GetMapping("/getFollowersDetails")
    List<User> getFollowerDetails(@RequestHeader("username") String userBusinessEmail){

        List<Engagement> engagements = iEngagementService.findByUserBusinessEmail(userBusinessEmail);
        List<User> users  = new ArrayList<>();
        for (Engagement engagement:engagements) {
            User user = iUserService.getUserDetails(engagement.getSecondaryEmail());
            users.add(user);
        }
        return users;
    }

    @GetMapping("/getFollowingDetails")
    List<User> getFollowingDetails(@RequestHeader("username") String secondaryEmail){

        List<Engagement> engagements = iEngagementService.findBySecondaryEmail(secondaryEmail);
        List<User> users  = new ArrayList<>();
        for (Engagement engagement:engagements) {
            User user = iUserService.getUserDetails(engagement.getUserBusinessEmail());
            users.add(user);
        }
        return users;
    }

    @GetMapping("/getFollowRequests")
    List<User> getFollowRequests(@RequestHeader("username") String userBusinessEmail){
        List<Engagement> engagements = iEngagementService.getFollowRequests(userBusinessEmail);
        List<User> users  = new ArrayList<>();
        for (Engagement engagement:engagements) {
            User user = iUserService.getUserDetails(engagement.getSecondaryEmail());
            users.add(user);
        }
        return users;
    }

    @GetMapping("/acceptFollowRequests/{secondaryEmail}")
    Long acceptFollowRequests(@PathVariable("secondaryEmail") String secondaryEmail, @RequestHeader("username") String userBusinessEmail){
        return iEngagementService.acceptFollowRequests(secondaryEmail, userBusinessEmail);
    }

    @GetMapping("/rejectFollowRequests/{secondaryEmail}")
    Long rejectFollowRequests(@PathVariable("secondaryEmail") String secondaryEmail, @RequestHeader("username") String userBusinessEmail){
        return iEngagementService.rejectFollowRequests(secondaryEmail, userBusinessEmail);
    }

    @GetMapping("/switchProfilePrivacy")
    Long switchProfilePrivacy(@RequestHeader("username") String userEmail){
        iEngagementService.acceptAllFollowRequests(userEmail);
        return iUserService.switchPrivacy(userEmail);
    }

    @PutMapping("/updateUserProfile")
    User updateUserProfile(@RequestBody User user,@RequestHeader("username") String userEmail ){
        user.setUserEmail(userEmail);
        return iUserService.addUser(user);
    }

    @PutMapping("/updateBusinessProfile")
    Business updateBusinessProfile(@RequestBody Business business, @RequestHeader("username") String businessEmail){
        business.setBusinessEmail(businessEmail);
        return iBusinessService.addBusiness(business);
    }



}
