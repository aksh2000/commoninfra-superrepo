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



    @GetMapping(value = "/getUserResponsesByUserIdAndQuizId/{quizId}")
    List<QuizResponses> findByUserIdAndQuizId(@RequestHeader("username") String userId, @PathVariable("quizId") Long quizId){
        return iQuizResponse.findByUserIdAndQuizId(userId, quizId);
    }

    @GetMapping(value = "/getSubscribedQuizzes")
    List<Quiz> getSubscribedQuizzes(@RequestHeader("username") String userId){
        List<QuizSubscribers> quizSubscribers = iQuizSubscriberService.getSubscribedQuizzes(userId);
        List<Quiz> quizList = new ArrayList<>();
        for (QuizSubscribers quizSubscribers1: quizSubscribers) {
            Optional<Quiz> quiz = iQuizService.findById(quizSubscribers1.getQuizId());
            quizList.add(quiz.get());
        }
        return quizList;
    }

    @PostMapping(value = "/addQuiz")
    Quiz addQuiz(@RequestBody Quiz quiz, @RequestHeader("username") String adminId){
        quiz.setAdminId(adminId);
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
    QuizSubscribers addQuizSubscriber(@RequestBody QuizSubscribers quizSubscribers, @RequestHeader("username") String userId){
        quizSubscribers.setUserId(userId);
        QuizLeaderBoard quizLeaderBoard = new QuizLeaderBoard();
        quizLeaderBoard.setQuizId(quizSubscribers.getQuizId());
        quizLeaderBoard.setUserId(quizSubscribers.getUserId());
        quizLeaderBoard.setTotalScore(0);
       QuizLeaderBoard quizLeaderBoard1 =  iQuizLeaderBoard.addLeaderBoard(quizLeaderBoard);
        System.out.println(quizLeaderBoard1);
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

    @GetMapping(value = "/getQuizListByAdminId")
    List<Quiz> getQuizListByAdminId(@RequestHeader("username") String adminId){
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


    @GetMapping(value = "/getPreviousQuizByAdminId")
    public List<Quiz> getPreviousQuiz(@RequestHeader("username") String adminId) {
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

    @GetMapping(value = "/getCurrentQuizByAdminId")
    public List<Quiz> getCurrentQuiz(@RequestHeader("username") String adminId) {
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

    @GetMapping(value = "/getFutureQuizByAdminId")
    public List<Quiz> getFutureQuiz(@RequestHeader("username") String adminId) {
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
    @GetMapping(value = "/getUserSubscriptionStatus/{quizId}")
    public QuizSubscribers getUserSubscriptionStatus(@PathVariable("quizId") Long quizId,@RequestHeader("username") String userId){
        return iQuizSubscriberService.getUserSubscriptionStatus(quizId,userId);
    }

    @PostMapping(value = "/skipInitialize")
    public SkipCount save(@RequestBody SkipCount skipCount,@RequestHeader("username") String userId) {
        //skipCount.setSkipCount(0);
        skipCount.setUserId(userId);
        return iSkipCountService.save(skipCount);
    }

    @GetMapping(value = "/getSkipCount/{quizId}")
    public SkipCount getCount(@PathVariable("quizId")long quizId, @RequestHeader("username") String userId) {
        SkipCount skipCount = iSkipCountService.findByQuizIdAndUserId(quizId,userId);
        int count = skipCount.getCount();
        if(count >= 3) {
            return skipCount;
        }
        else {
            skipCount.setCount(count+1);
            return iSkipCountService.save(skipCount);
        }
    }

    @PostMapping("/addQuizResponse")
    public QuizResponses addResponse(@RequestBody QuizResponses quizResponses, @RequestHeader("username") String userId){
        quizResponses.setUserId(userId);
        System.out.println("Question id = "+quizResponses.getQuestionId());
        QuestionDetails questionDetails = restTemplate.getForObject("http://CMS-ADMIN/cmsAdmin/getQuestionDetails/"+quizResponses.getQuestionId(),QuestionDetails.class);

        assert questionDetails != null;
        double score = Methods.getScore(quizResponses,questionDetails);
        quizResponses.setQuestionScore(score);
        int modified = iQuizLeaderBoard.updateLeaderBoard(quizResponses.getQuizId(), quizResponses.getUserId(), score);
        return  iQuizResponse.addQuizResponse(quizResponses);
    }




    @GetMapping(value = "/canStart/{quizId}")
    public boolean canStart(@PathVariable("quizId") Long quizId){
        Quiz quiz = iQuizService.findById(quizId).get();
        Date d = new Date();
        Date quizDate = quiz.getStartTime();
        return quizDate.compareTo(d) < 0 ? true : false;
    }

    @GetMapping(value = "/quizStarted/{quizId}")
    public int quizStarted(@RequestHeader("username") String userId,@PathVariable("quizId") Long quizId){
        return iQuizSubscriberService.updateStartTime(userId,quizId);
    }

    @GetMapping(value = "/quizEnded/{quizId}")
    public int quizEnded(@RequestHeader("username") String userId,@PathVariable("quizId") Long quizId){
        return iQuizSubscriberService.updateEndTime(userId,quizId);
    }

    @GetMapping(value = "/getSolvedQuestions/{quizId}")
    public List<QuizResponses> getSolvedQuestions(@RequestHeader("username") String userId,@PathVariable("quizId") Long quizId){
        return iQuizResponse.findByUserIdAndQuizId(userId,quizId);
    }

    @GetMapping(value = "/getRemainingTime/{quizId}")
    public long getRemainingTime(@PathVariable("quizId") Long quizId,@RequestHeader("username") String userId){
        QuizSubscribers quizSubscribers = iQuizSubscriberService.findByQuizIdAndUserId(quizId,userId);
        Date startTime = quizSubscribers.getUserStartTime();
        Date date = new Date();
        long differenceInSeconds = (date.getTime() - startTime.getTime()) / 1000;
        //System.out.println(differenceInSeconds);
        return differenceInSeconds;
    }

    @GetMapping("/countQuizQuestions/{quizId}")
    Long countQuizQuestions(@PathVariable("quizId") Long quizId){
        return iQuizQuestionsService.countQuizQuestions(quizId);
    }

    @GetMapping("/getBroadcastedDynamicQuizQuestions/{quizId}")
    List<Long> getBroadcastedDynamicQuizQuestions(@PathVariable("quizId") Long quizId){
        return iQuizResponse.getBroadcastedDynamicQuizQuestions(quizId);
    }
    @GetMapping("/setEndTime/{quizId}")
    Quiz setEndTime(@PathVariable("quizId") Long quizId){
        return iQuizService.setEndTime(quizId);
    }

    @GetMapping("/getMainLeaderBoard")
    List<LeaderBoardList> getMainLeaderBoard(){
        return iQuizService.getMainLeaderBoard();
    }
}
