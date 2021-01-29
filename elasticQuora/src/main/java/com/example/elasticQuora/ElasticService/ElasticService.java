package com.example.elasticQuora.ElasticService;

import com.example.elasticQuora.ElasticEntity.Business;
//import com.example.elasticQuora.ElasticEntity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
public interface ElasticService {
    public void deleteById(String id);


    public List<Business> findAll();

    public Business save(Business employee);


    public Business findById(String id);
    public List<Business> generalSearchUser(String name);
}
