package com.cms.quiz.controller;

import com.cms.quiz.entity.Quiz;
import com.cms.quiz.entity.QuizResponses;
import com.cms.quiz.service.IQuizResponse;
import com.cms.quiz.service.IQuizService;
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
    IQuizResponse iQuizResponse;

    @GetMapping(value = "/getQuizDetails/{quizId}")
    public Optional<Quiz> findById(@PathVariable("quizId") Long quizId){
        return iQuizService.findById(quizId);
    }

    @PostMapping(value = "/addQuizResponse")
    public QuizResponses addQuizResponse(@RequestBody QuizResponses quizResponses){
        return iQuizResponse.addQuizResponse(quizResponses);
    }

    @GetMapping("/getUserResponsesByUserIdAndQuizId/{userId}/{quizId}")
    List<QuizResponses> findByUserIdAndQuizId(@PathVariable("userId") String userId, @PathVariable("quizId") Long quizId){
        return iQuizResponse.findByUserIdAndQuizId(userId, quizId);
    }

}
