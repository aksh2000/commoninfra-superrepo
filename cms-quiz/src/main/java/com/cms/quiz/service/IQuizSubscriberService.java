package com.cms.quiz.service;

import com.cms.quiz.entity.Quiz;
import com.cms.quiz.entity.QuizSubscribers;

import java.util.List;

public interface IQuizSubscriberService {
    QuizSubscribers addQuizSubscriber(QuizSubscribers quizSubscribers);

    List<QuizSubscribers> getQuizSubscribers(Long quizId);

    List<Quiz> getSubscribedQuizzes(String userId);

    QuizSubscribers getUserSubscriptionStatus(Long quizId, String userId);
}
