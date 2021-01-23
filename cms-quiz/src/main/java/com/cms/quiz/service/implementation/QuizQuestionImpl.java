package com.cms.quiz.service.implementation;

import com.cms.quiz.entity.QuizQuestions;
import com.cms.quiz.repository.QuizQuestionsRepository;
import com.cms.quiz.service.IQuizQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizQuestionImpl implements IQuizQuestionsService {
    @Autowired
    QuizQuestionsRepository quizQuestionsRepository;
    @Override
    public QuizQuestions addQuizQuestion(QuizQuestions quizQuestions) {
        return quizQuestionsRepository.save(quizQuestions);
    }

    @Override
    public List<QuizQuestions> getQuizQuestions(Long quizId) {
        return quizQuestionsRepository.findByQuizId(quizId);
    }

    @Override
    public QuizQuestions deleteQuestion(Long quizId, Long questionId) {
        return quizQuestionsRepository.deleteByQuizIdAndQuestionId(quizId,questionId);
    }
}
