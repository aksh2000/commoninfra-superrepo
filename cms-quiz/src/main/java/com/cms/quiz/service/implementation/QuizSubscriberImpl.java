package com.cms.quiz.service.implementation;

import com.cms.quiz.entity.QuizSubscribers;
import com.cms.quiz.repository.QuizSubscribersRepository;
import com.cms.quiz.service.IQuizSubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
}
