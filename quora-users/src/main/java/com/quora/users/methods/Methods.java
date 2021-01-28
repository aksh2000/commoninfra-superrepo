package com.quora.users.methods;

import com.quora.users.entity.User;
import com.quora.users.service.IUserService;

public class Methods {
    public static boolean isUserEmail(IUserService iUserService, String email){
        User user = iUserService.getUserDetails(email);
        if(user == null){
            return false;
        }
        return true;
    }
}
