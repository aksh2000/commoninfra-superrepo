package com.cms.quiz.service;

import com.cms.quiz.entity.Quiz;

import java.util.Optional;

public interface IQuizService {
    Quiz addQuiz(Quiz quiz);
    Optional<Quiz> findById(Long quizId);
    Optional<Quiz> getQuizDetails(Long quizId);
}
