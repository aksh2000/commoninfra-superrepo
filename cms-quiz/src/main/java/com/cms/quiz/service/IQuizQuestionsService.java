package com.cms.quiz.service;

import com.cms.quiz.entity.QuizQuestions;

import java.util.List;
import java.util.Optional;

public interface IQuizQuestionsService {
    QuizQuestions addQuizQuestion(QuizQuestions quizQuestions);

    List<QuizQuestions> getQuizQuestions(Long quizId);

    QuizQuestions deleteQuestion(Long quizId, Long questionId);
}
