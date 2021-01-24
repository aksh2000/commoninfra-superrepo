package com.cms.admin.service.implementation;

import com.cms.admin.entity.Admin;
import com.cms.admin.repository.AdminRepository;
import com.cms.admin.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    AdminRepository adminRepository;
    @Override
    public Optional<Admin> findById(String adminId) {
        return adminRepository.findById(adminId);
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}
