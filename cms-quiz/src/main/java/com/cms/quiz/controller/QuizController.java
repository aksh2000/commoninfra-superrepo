package com.cms.quiz.controller;

import com.cms.quiz.dto.QuestionDetails;
import com.cms.quiz.entity.*;
import com.cms.quiz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.ws.rs.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

    @GetMapping(value = "/getSubscribedQuizs/{userId}")
    List<Quiz> getSubscribedQuizs(@PathVariable("userId") String userId){
        return iQuizSubscriberService.getSubscribedQuizs(userId);
    }

    @PostMapping(value = "/addQuiz")
    Quiz addQuiz(@RequestBody Quiz quiz){
        return iQuizService.addQuiz(quiz);
    }

    @GetMapping(value = "/getQuizDetails/{quizId}")
    Optional<Quiz> getQuizDetails(@PathVariable("quizId") Long quizId){
        return iQuizService.getQuizDetails(quizId);
    }

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
    @GetMapping(value = "/getQuizQuestionsWithContent/{quizId}")
    List<QuestionDetails> getQuizQuestionsWithContent(@PathVariable("quizId") Long quizId){
        return iQuizQuestionsService.getQuizQuestionsWithContent(quizId);
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

    @GetMapping(value = "/getStaticQuiz")
    public List<Quiz> getStaticQuiz(){
        return iQuizService.getStaticQuiz();
    }

    @GetMapping(value = "/getDynamicQuiz")
    public List<Quiz> getDynamicQuiz(){
        return iQuizService.getDynamicQuiz();
    }


    @GetMapping(value = "/getPreviousQuizByAdminId/{adminId}")
    public List<Quiz> getPreviousQuiz(@PathVariable("adminId") String adminId) {
        List<Quiz> quizList = iQuizService.getQuizListByAdminId(adminId);
        List<Quiz> quizList1 = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        for(Quiz quiz : quizList) {
            if(date.compareTo(quiz.getEndTime())>0) {
                quizList1.add(quiz);
            }
        }
        return quizList1;
    }

    @GetMapping(value = "/getCurrentQuizByAdminId/{adminId}")
    public List<Quiz> getCurrentQuiz(@PathVariable("adminId") String adminId) {
        List<Quiz> quizList = iQuizService.getQuizListByAdminId(adminId);
        List<Quiz> quizList1 = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        for(Quiz quiz : quizList) {
            if(date.compareTo(quiz.getEndTime())<0 && date.compareTo(quiz.getStartTime())>0) {
                quizList1.add(quiz);
            }
        }
        return quizList1;
    }

    @GetMapping(value = "/getFutureQuizByAdminId/{adminId}")
    public List<Quiz> getFutureQuiz(@PathVariable("adminId") String adminId) {
        List<Quiz> quizList = iQuizService.getQuizListByAdminId(adminId);
        List<Quiz> quizList1 = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();

        for(Quiz quiz : quizList) {
            System.out.println(quiz.getStartTime());
            if(date.compareTo(quiz.getStartTime())<0) {
                quizList1.add(quiz);
            }
        }
        return quizList1;
    }
}
