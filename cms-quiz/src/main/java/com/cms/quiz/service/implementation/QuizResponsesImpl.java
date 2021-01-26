package com.cms.quiz.service.implementation;

import com.cms.quiz.entity.QuizLeaderBoard;
import com.cms.quiz.entity.QuizResponses;
import com.cms.quiz.repository.QuizResponsesRepository;
import com.cms.quiz.service.IQuizLeaderBoard;
import com.cms.quiz.service.IQuizResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizResponsesImpl implements IQuizResponseService {

    @Autowired
    QuizResponsesRepository quizResponsesRepositry;



    @Override
    public QuizResponses addQuizResponse(QuizResponses quizResponses) {
        return quizResponsesRepositry.save(quizResponses);
    }

    @Override
    public List<QuizResponses> findByUserIdAndQuizId(String userId, Long quizId) {
        return quizResponsesRepositry.findByUserIdAndQuizId(userId,quizId);
    }
}