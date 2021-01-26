package com.cms.quiz.service.implementation;

import com.cms.quiz.entity.Quiz;
import com.cms.quiz.entity.QuizSubscribers;
import com.cms.quiz.repository.QuizSubscribersRepository;
import com.cms.quiz.service.IQuizSubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuizSubscriberImpl implements IQuizSubscriberService {
    @Autowired
    QuizSubscribersRepository quizSubscribersRepository;
    @Override
    public QuizSubscribers addQuizSubscriber(QuizSubscribers quizSubscribers) {
        return quizSubscribersRepository.save(quizSubscribers);
    }

    @Override
    public List<QuizSubscribers> getQuizSubscribers(Long quizId) {
        return quizSubscribersRepository.findByQuizId(quizId);
    }

    @Override
    public List<Quiz> getSubscribedQuizs(String userId) {
        return quizSubscribersRepository.findByUserId(userId);
    }

    @Override
    public int updateStartTime(String userId, Long quizId) {
        Date d = new Date();
        return quizSubscribersRepository.upDateStartTime(d,userId,quizId);
    }

    @Override
    public int updateEndTime(String userId, Long quizId) {
        Date currentDate = new Date();
        return quizSubscribersRepository.upDateEndTime(currentDate,userId,quizId);
    }
}
