package com.quora.users.service.impl;

import com.quora.users.entity.Business;
import com.quora.users.repository.BusinessRepository;
import com.quora.users.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements IBusinessService {
    @Autowired
    BusinessRepository businessRepository;

    @Override
    public Business addBusiness(Business business) {
        return businessRepository.save(business);
    }

    @Override
    public Business getBusinessDetails(String businessEmail) {
        return businessRepository.findById(businessEmail).get();
    }
}
