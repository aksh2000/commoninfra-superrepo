package com.cms.quiz.service;

import com.cms.quiz.entity.Quiz;
import com.cms.quiz.entity.QuizSubscribers;

import java.util.Date;
import java.util.List;

public interface IQuizSubscriberService {
    QuizSubscribers addQuizSubscriber(QuizSubscribers quizSubscribers);

    List<QuizSubscribers> getQuizSubscribers(Long quizId);

    List<QuizSubscribers> getSubscribedQuizzes(String userId);

    QuizSubscribers getUserSubscriptionStatus(Long quizId, String userId);
    int updateStartTime(String userId, Long quizId);

    int updateEndTime(String userId, Long quizId);

    QuizSubscribers findByQuizIdAndUserId(Long quizId, String userId);
    int updateEndTimeOfDynamicQuizSubscribers(Long quizId);
}
