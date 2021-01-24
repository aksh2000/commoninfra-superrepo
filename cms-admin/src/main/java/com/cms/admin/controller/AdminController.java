package com.cms.admin.controller;

import com.cms.admin.entity.Admin;
import com.cms.admin.entity.Questions;
import com.cms.admin.service.IAdminService;
import com.cms.admin.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/cmsAdmin")
public class AdminController {

    @Autowired
    IAdminService iAdminService;

    @Autowired
    IQuestionService iQuestionService;

    @GetMapping(value = "/test")
    public String pt(){
        System.out.println("bsddsnms nmfs ");
        return "Test";
    }

    @GetMapping(value = "/getAdminDetails/{adminId}")
    public Optional<Admin> findById(@PathVariable("adminId") Long adminId){
        return iAdminService.findById(adminId);
    }

    @PostMapping(value = "/addAdmin")
    public Admin addAdmin(@RequestBody Admin admin){
        System.out.println("Adding Admin");
        return iAdminService.addAdmin(admin);
    }

    @GetMapping(value = "/getQuestionDetails/{questionId}")
    public Optional<Questions> getQuestions(@PathVariable("questionId") Long questionId){
        return iQuestionService.getQuestions(questionId);
    }

    @PostMapping(value = "/addQuestion")
    public Questions saveQuestion(@RequestBody Questions questions){
        return iQuestionService.saveQuestions(questions);
    }
    @GetMapping(value = "/getQuestionsByCategory/{categoryId}")
    List<Questions> findByCategoryId(@PathVariable("categoryId") Long categoryId){
        return iQuestionService.findByCategoryId(categoryId);
    }
}
