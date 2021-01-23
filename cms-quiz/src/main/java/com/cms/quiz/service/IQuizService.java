package com.cms.quiz.service;

import com.cms.quiz.entity.Quiz;

import java.util.Optional;

public interface IQuizService {
    Optional<Quiz> findById(Long quizId);
}
