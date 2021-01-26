package com.cms.admin.controller;

import com.cms.admin.entity.Admin;
import com.cms.admin.entity.NonScreenedQuestions;
import com.cms.admin.entity.Questions;
import com.cms.admin.service.IAdminService;
import com.cms.admin.service.IQuestionService;
import com.cms.admin.service.implementation.NonScreenedQuestionsServiceImpl;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*")
@CrossOrigin
@RestController
@RequestMapping(value = "/cmsAdmin")
public class AdminController {

    @Autowired
    IAdminService iAdminService;

    @Autowired
    IQuestionService iQuestionService;

    @Autowired
    NonScreenedQuestionsServiceImpl nonScreenedQuestionsService;

    @GetMapping(value = "/getAdminDetails/{adminId}")
    public Optional<Admin> findById(@PathVariable("adminId") String adminId){
        return iAdminService.findById(adminId);
    }

    @PostMapping(value = "/addAdmin")
    public Admin addAdmin(@RequestBody Admin admin){
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
    public List<Questions> getQuestionsByCategory(@PathVariable("categoryId") long categoryId) {
        return iQuestionService.getQuestionsByCategory(categoryId);
    }

    @GetMapping(value = "/getQuestionsByCategoryAndType/{categoryId}/{type}")
    public List<Questions> getQuestionsByCategoryAndType(@PathVariable("categoryId") long categoryId,
                                                         @PathVariable("type")int type){
        return iQuestionService.getQuestionsByCategoryAndType(categoryId,type);
    }

    @GetMapping(value="/getFirstNonScreened")
    public NonScreenedQuestions getFirstNonScreened()
    {
        NonScreenedQuestions nsc= nonScreenedQuestionsService.findFirstElement().get();
        nonScreenedQuestionsService.deleteById(nsc.getQuestionId());
        return nsc;
    }

    @GetMapping(value = "/getQuestionsByType/{type}")
    public List<Questions> findByType(@PathVariable("type") int type){
        return iQuestionService.findByType(type);
    }

    @GetMapping(value = "/getAllQuestions")
    List<Questions> getAllQuestions(){
        return iQuestionService.getAllQuestions();
    }

}
