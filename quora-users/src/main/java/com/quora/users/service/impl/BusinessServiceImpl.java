package com.quora.users.service.impl;

import com.quora.users.entity.Business;
import com.quora.users.repository.BusinessRepository;
import com.quora.users.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BusinessServiceImpl implements IBusinessService {
    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Business addBusiness(Business business) {
        try{
            Business business1 = restTemplate.postForObject("http://localhost:8082/search/saveUser",business,Business.class);
        }
        catch (Exception e){
            System.out.println("Unable to add the business in search database!!");
            System.out.println(e);
        }
        return businessRepository.save(business);
    }

    @Override
    public Business getBusinessDetails(String businessEmail) {
        return businessRepository.findById(businessEmail).get();
    }
}
