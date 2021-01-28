package com.cms.quiz.service.implementation;

import com.cms.quiz.entity.QuizResponses;
import com.cms.quiz.repository.QuizResponsesRepository;
import com.cms.quiz.service.IQuizResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizResponsesImpl implements IQuizResponseService {

    @Autowired
    QuizResponsesRepository quizResponsesRepositry;

    @Override
    public List<Long> getBroadcastedDynamicQuizQuestions(Long quizId) {
        return quizResponsesRepositry.getBroadcastedDynamicQuizQuestions(quizId);
    }


    @Override
    public QuizResponses addQuizResponse(QuizResponses quizResponses) {
        return quizResponsesRepositry.save(quizResponses);
    }

    @Override
    public List<QuizResponses> findByUserIdAndQuizId(String userId, Long quizId) {
        return quizResponsesRepositry.findByUserIdAndQuizId(userId,quizId);
    }

    @Override
    public Long getMostAnsweredQuestionId(Long quizId) {
        List<Object[]> objects = quizResponsesRepositry.getMostAnsweredQuestionId(quizId);
        return Long.parseLong(objects.get(0)[1]+"");
    }
}
