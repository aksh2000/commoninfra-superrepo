package com.cms.admin.service;

import com.cms.admin.entity.Admin;

import java.util.Optional;

public interface IAdminService {
    Optional<Admin> findById(String adminId);

    Admin addAdmin(Admin admin);
}
