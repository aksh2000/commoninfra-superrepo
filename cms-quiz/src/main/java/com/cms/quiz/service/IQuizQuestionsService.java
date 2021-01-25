package com.cms.quiz.service;

import com.cms.quiz.dto.QuestionDetails;
import com.cms.quiz.entity.QuizQuestions;

import java.util.List;

public interface IQuizQuestionsService {
    QuizQuestions addQuizQuestion(QuizQuestions quizQuestions);

    List<QuizQuestions> getQuizQuestions(Long quizId);

    List<QuizQuestions> deleteQuestion(Long quizId, Long questionId);

    List<QuestionDetails> getQuizQuestionsWithContent(Long quizId);
}
