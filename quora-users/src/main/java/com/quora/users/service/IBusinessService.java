package com.quora.users.service;

import com.quora.users.entity.Business;

public interface IBusinessService {
    Business addBusiness(Business business);

    Business getBusinessDetails(String businessEmail);
}
