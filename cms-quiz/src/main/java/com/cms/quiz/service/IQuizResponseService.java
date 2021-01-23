package com.cms.quiz.service;

import com.cms.quiz.entity.Quiz;
import com.cms.quiz.entity.QuizResponses;

import java.util.List;

public interface IQuizResponseService {
    QuizResponses addQuizResponse(QuizResponses quizResponses);

    List<QuizResponses> findByUserIdAndQuizId(String userId, Long quizId);


}
