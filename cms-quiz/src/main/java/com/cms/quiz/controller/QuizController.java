package com.cms.quiz.controller;

import com.cms.quiz.entity.*;
import com.cms.quiz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cmsQuiz")
public class QuizController {

    @Autowired
    IQuizService iQuizService;

    @Autowired
    IQuizResponseService iQuizResponse;

    @Autowired
    ICategoryService iCategoryService;

    @Autowired
    IQuizQuestionsService iQuizQuestionsService;

    @Autowired
    IQuizSubscriberService iQuizSubscriberService;

    @GetMapping("/getQuizDetails/{quizId}")
    public Optional<Quiz> findById(@PathVariable("quizId") Long quizId){
        return iQuizService.findById(quizId);
    }

    @PostMapping("/addQuizResponse")
    public QuizResponses addQuizResponse(@RequestBody QuizResponses quizResponses){
        return iQuizResponse.addQuizResponse(quizResponses);
    }

    @GetMapping("/getUserResponsesByUserIdAndQuizId/{userId}/{quizId}")
    List<QuizResponses> findByUserIdAndQuizId(@PathVariable("userId") String userId, @PathVariable("quizId") Long quizId){
        return iQuizResponse.findByUserIdAndQuizId(userId, quizId);
    }

    @PostMapping("/addQuiz")
    Quiz addQuiz(@RequestBody Quiz quiz){
        return iQuizService.addQuiz(quiz);
    }

    @GetMapping("/getQuizDetails/{quizId}")
    Optional<Quiz> getQuizDetails(@PathVariable("quizId") Long quizId){
        return iQuizService.getQuizDetails(quizId);
    }

    @PostMapping("/addCategory")
    Category addCategory(@RequestBody Category category){
        return iCategoryService.addCategory(category);
    }

    @GetMapping("/getCategoryDetails/{categoryId}")
    Optional<Category> getCategoryDetails(@PathVariable("categoryId") Long categoryId){
        return iCategoryService.getCategoryDetails(categoryId);
    }

    @PostMapping("/addQuizQuestion")
    QuizQuestions addQuizQuestion(@RequestBody QuizQuestions quizQuestions){
        return iQuizQuestionsService.addQuizQuestion(quizQuestions);
    }

    @GetMapping("/getQuizQuestions/{quizId}")
    List<QuizQuestions> getQuizQuestions(@PathVariable Long quizId){
        return iQuizQuestionsService.getQuizQuestions(quizId);
    }

    @PostMapping("/addQuizSubscriber")
    QuizSubscribers addQuizSubscriber(@RequestBody QuizSubscribers quizSubscribers){
        return iQuizSubscriberService.addQuizSubscriber(quizSubscribers);
    }

    @GetMapping("getQuizSubscribers/{quizId}")
    List<QuizSubscribers> getQuizSubscribers(@PathVariable("quizId") Long quizId ){
        return iQuizSubscriberService.getQuizSubscribers(quizId);
    }

}
