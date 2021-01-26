package com.cms.quiz.controller;

import com.cms.quiz.dto.BroadcastQuestion;
import com.cms.quiz.dto.LeaderBoardList;
import com.cms.quiz.dto.QuestionDetails;
import com.cms.quiz.entity.*;
import com.cms.quiz.methods.Methods;
import com.cms.quiz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
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

    @Autowired
    ISkipCountService iSkipCountService;

    @Autowired
    IQuizLeaderBoard iQuizLeaderBoard;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/getQuizDetails/{quizId}")
    public Optional<Quiz> findById(@PathVariable("quizId") Long quizId){
        return iQuizService.findById(quizId);
    }



    @GetMapping(value = "/getUserResponsesByUserIdAndQuizId/{userId}/{quizId}")
    List<QuizResponses> findByUserIdAndQuizId(@PathVariable("userId") String userId, @PathVariable("quizId") Long quizId){
        return iQuizResponse.findByUserIdAndQuizId(userId, quizId);
    }

    @GetMapping(value = "/getSubscribedQuizzes/{userId}")
    List<Quiz> getSubscribedQuizzes(@PathVariable("userId") String userId){
        return iQuizSubscriberService.getSubscribedQuizzes(userId);
    }

    @PostMapping(value = "/addQuiz")
    Quiz addQuiz(@RequestBody Quiz quiz){
        return iQuizService.addQuiz(quiz);
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
        QuizLeaderBoard quizLeaderBoard = new QuizLeaderBoard();
        quizLeaderBoard.setQuizId(quizSubscribers.getQuizId());
        quizLeaderBoard.setUserId(quizSubscribers.getUserId());
        quizLeaderBoard.setTotalScore(0);
        iQuizLeaderBoard.addLeaderBoard(quizLeaderBoard);
        return iQuizSubscriberService.addQuizSubscriber(quizSubscribers);
    }

    @GetMapping(value = "/getQuizSubscribers/{quizId}")
    List<QuizSubscribers> getQuizSubscribers(@PathVariable("quizId") Long quizId ){
        return iQuizSubscriberService.getQuizSubscribers(quizId);
    }

    @Transactional
    @DeleteMapping(value = "/deleteQuestion/{quizId}/{questionId}")
    public List<QuizQuestions> deleteQuestion(@PathVariable("quizId") Long quizId,@PathVariable("questionId") Long questionId){
        return iQuizQuestionsService.deleteQuestion(quizId,questionId);
    }

    @GetMapping(value = "/getQuizListByAdminId/{adminId}")
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
        Date date = new Date();
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
        Date date = new Date();
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
        Date date = new Date();

        for(Quiz quiz : quizList) {
            System.out.println(quiz.getStartTime());
            if(date.compareTo(quiz.getStartTime())<0) {
                quizList1.add(quiz);
            }
        }
        return quizList1;
    }

    @PostMapping(value = "/broadcastQuestion")
    public BroadcastQuestion broadcastQuestion(@RequestBody BroadcastQuestion broadcastQuestion){
        return broadcastQuestion;
    }

    @GetMapping(value = "/getLeaderBoard/{quizId}")
    public List<LeaderBoardList> getLeaderBoard(@PathVariable("quizId") Long quizId){
        return iQuizService.getLeaderBoard(quizId);
    }
    @GetMapping(value = "/getUserSubscriptionStatus/{quizId}/{userId}")
    public QuizSubscribers getUserSubscriptionStatus(@PathVariable("quizId") Long quizId,@PathVariable("userId")String userId){
        return iQuizSubscriberService.getUserSubscriptionStatus(quizId,userId);
    }

    @PostMapping(value = "/skipInitialize")
    public void save(@RequestBody SkipCount skipCount) {
        skipCount.setSkipCount(0);
        iSkipCountService.save(skipCount);
    }

    @GetMapping(value = "/getSkipCount/{quizId}/{userId}")
    public SkipCount getCount(@PathVariable("quizId")long quizId, @PathVariable("userId")String userId) {
        SkipCount skipCount = iSkipCountService.findByQuizIdAndUserId(quizId,userId);
        int count = skipCount.getSkipCount();
        if(count >= 3) {
            return skipCount;
        }
        else {
            skipCount.setSkipCount(count+1);
            return iSkipCountService.save(skipCount);
        }
    }

    @PostMapping("/addQuizResponse")
    public QuizResponses addResponse(@RequestBody QuizResponses quizResponses){
        System.out.println("Question id = "+quizResponses.getQuestionId());
        QuestionDetails questionDetails = restTemplate.getForObject("http://CMS-ADMIN/cmsAdmin/getQuestionDetails/"+quizResponses.getQuestionId(),QuestionDetails.class);

        assert questionDetails != null;
        double score = Methods.getScore(quizResponses,questionDetails);
        quizResponses.setQuestionScore(score);
        int modified = iQuizLeaderBoard.updateLeaderBoard(quizResponses.getQuizId(), quizResponses.getUserId(), score);
        return  iQuizResponse.addQuizResponse(quizResponses);
    }




    @GetMapping(value = "cmsQuiz/canStart/{quizId}")
    public boolean canStart(@PathVariable("quizId") Long quizId){
        Quiz quiz = iQuizService.findById(quizId).get();
        Date d = new Date();
        Date quizDate = quiz.getStartTime();
        if(quizDate.compareTo(d) < 0 ){
            return true;
        }
        else return false;
    }

    @GetMapping(value = "/cmsQuiz/quizStarted/{userId}/{quizId}")
    public int quizStarted(@PathVariable("userId") String userId,@PathVariable("quizId") Long quizId){
        return iQuizSubscriberService.updateStartTime(userId,quizId);
    }

    @GetMapping(value = "/cmsQuiz/quizEnded/{userId}/{quizId}")
    public int quizEnded(@PathVariable("userId") String userId,@PathVariable("quizId") Long quizId){
        return iQuizSubscriberService.updateEndTime(userId,quizId);
    }


}
