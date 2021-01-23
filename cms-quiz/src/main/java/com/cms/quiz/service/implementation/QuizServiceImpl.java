package com.cms.quiz.service.implementation;

import com.cms.quiz.entity.Quiz;
import com.cms.quiz.repository.QuizRepository;
import com.cms.quiz.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizServiceImpl implements IQuizService {

    @Autowired
    QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Optional<Quiz> findById(Long quizId) {
        return quizRepository.findById(quizId);
    }

    @Override
    public Optional<Quiz> getQuizDetails(Long quizId) {
        return quizRepository.findById(quizId);
    }
}
