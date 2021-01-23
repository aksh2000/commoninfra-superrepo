package com.cms.quiz.service.implementation;

import com.cms.quiz.entity.QuizResponses;
import com.cms.quiz.repository.QuizResponsesRepositry;
import com.cms.quiz.service.IQuizResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuizResponsesImpl implements IQuizResponse {

    @Autowired
    QuizResponsesRepositry quizResponsesRepositry;

    @Override
    public QuizResponses addQuizResponse(QuizResponses quizResponses) {
        return quizResponsesRepositry.save(quizResponses);
    }

    @Override
    public List<QuizResponses> findByUserIdAndQuizId(String userId, Long quizId) {
        return quizResponsesRepositry.findByUserIdAndQuizId(userId,quizId);
    }
}
