package com.cms.admin.service;

import com.cms.admin.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface IAdminService {
    Optional<Admin> findById(String adminId);

    Admin addAdmin(Admin admin);
}
