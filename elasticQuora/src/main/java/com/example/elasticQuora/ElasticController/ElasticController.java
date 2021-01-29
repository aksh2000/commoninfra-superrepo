package com.example.elasticQuora.ElasticController;

import com.example.elasticQuora.ElasticEntity.Business;
import com.example.elasticQuora.ElasticService.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class ElasticController {

    @Autowired
    ElasticService elasticService;

    @GetMapping(value="/findAll")
    List<Business> findAll(){
        return elasticService.findAll();
    }

    @GetMapping(value="/findUser/{userId}")
    Business findById(@PathVariable("userId") String userId){
        return elasticService.findById(userId);
    }

    @PostMapping(value="/saveUser")
    public Business save(@RequestBody Business business){
        return elasticService.save(business);
    }

    @DeleteMapping(value="/deleteUser/{userId}")
    public void deleteById(@PathVariable("userId") String userId){
        elasticService.deleteById(userId);
    }

    @PutMapping(value="/editUser")
    public Business edit(@RequestBody Business user){
        return elasticService.save(user);
    }

    @GetMapping(value="/find/{userName}")
    public List<Business> findUser(@PathVariable("userName") String userName){
        return elasticService.generalSearchUser(userName);

    }
}
