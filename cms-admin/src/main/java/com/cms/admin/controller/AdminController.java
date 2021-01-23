package com.cms.admin.controller;

import com.cms.admin.entity.Admin;
import com.cms.admin.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/cmsAdmin")
public class AdminController {

    @Autowired
    IAdminService iAdminService;

    @GetMapping(value = "/getAdminDetails/{adminId}")
    public Optional<Admin> findById(@PathVariable("adminId") Long adminId){
        return iAdminService.findById(adminId);
    };

    @PostMapping(value = "/add")
    public Admin addAdmin(@RequestBody Admin admin){
        return iAdminService.addAdmin(admin);
    }

}
