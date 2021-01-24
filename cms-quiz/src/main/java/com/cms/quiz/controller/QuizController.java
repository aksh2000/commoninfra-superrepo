package com.cms.quiz.controller;

import com.cms.quiz.entity.*;
import com.cms.quiz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Optional;

@CrossOrigin
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

    @GetMapping(value = "/getQuizDetails/{quizId}")
    public Optional<Quiz> findById(@PathVariable("quizId") Long quizId){
        return iQuizService.findById(quizId);
    }

    @PostMapping(value = "/addQuizResponse")
    public QuizResponses addQuizResponse(@RequestBody QuizResponses quizResponses){
        return iQuizResponse.addQuizResponse(quizResponses);
    }

    @GetMapping(value = "/getUserResponsesByUserIdAndQuizId/{userId}/{quizId}")
    List<QuizResponses> findByUserIdAndQuizId(@PathVariable("userId") String userId, @PathVariable("quizId") Long quizId){
        return iQuizResponse.findByUserIdAndQuizId(userId, quizId);
    }

    @PostMapping(value = "/addQuiz")
    Quiz addQuiz(@RequestBody Quiz quiz){
        return iQuizService.addQuiz(quiz);
    }

//    @GetMapping(value = "/getQuizDetails/{quizId}")
//    Optional<Quiz> getQuizDetails(@PathVariable("quizId") Long quizId){
//        return iQuizService.getQuizDetails(quizId);
//    }

    @PostMapping(value = "/addCategory")
    Category addCategory(@RequestBody Category category){
        return iCategoryService.addCategory(category);
    }

    @GetMapping(value = "/getCategoryDetails/{categoryId}")
    Optional<Category> getCategoryDetails(@PathVariable("categoryId") Long categoryId){
        return iCategoryService.getCategoryDetails(categoryId);
    }

    @PostMapping(value = "/addQuizQuestion")
    QuizQuestions addQuizQuestion(@RequestBody QuizQuestions quizQuestions){
        return iQuizQuestionsService.addQuizQuestion(quizQuestions);
    }

    @GetMapping(value = "/getQuizQuestions/{quizId}")
    List<QuizQuestions> getQuizQuestions(@PathVariable Long quizId){
        return iQuizQuestionsService.getQuizQuestions(quizId);
    }

    @PostMapping(value = "/addQuizSubscriber")
    QuizSubscribers addQuizSubscriber(@RequestBody QuizSubscribers quizSubscribers){
        return iQuizSubscriberService.addQuizSubscriber(quizSubscribers);
    }

    @GetMapping(value = "getQuizSubscribers/{quizId}")
    List<QuizSubscribers> getQuizSubscribers(@PathVariable("quizId") Long quizId ){
        return iQuizSubscriberService.getQuizSubscribers(quizId);
    }

    @Transactional
    @DeleteMapping(value = "/deleteQuestion/{quizId}/{questionId}")
    public List<QuizQuestions> deleteQuestion(@PathVariable("quizId") Long quizId,@PathVariable("questionId") Long questionId){
        return iQuizQuestionsService.deleteQuestion(quizId,questionId);
    }

    @GetMapping(value = "getQuizListByAdminId/{adminId}")
    List<Quiz> getQuizListByAdminId(@PathVariable("adminId") String adminId){
        return iQuizService.getQuizListByAdminId(adminId);
    }

    @GetMapping(value = "/getAllCategories")
    public List<Category> getAllCategories() {
        return iCategoryService.findAll();
    }
}
