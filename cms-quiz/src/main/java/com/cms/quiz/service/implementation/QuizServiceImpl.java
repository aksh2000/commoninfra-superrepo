package com.cms.quiz.service.implementation;

import com.cms.quiz.entity.Quiz;
import com.cms.quiz.repository.QuizRepository;
import com.cms.quiz.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

    @Override
    public List<Quiz> getQuizListByAdminId(String adminId) {
        return quizRepository.findByAdminId(adminId);
    }

    @Override
    public List<Quiz> getStaticQuiz() {
        Date d=new Date();
        return quizRepository.getStaticQuiz(d);
    }

    @Override
    public List<Quiz> getDynamicQuiz() {
        Date d=new Date();
        return quizRepository.getDynamicQuiz(d);
    }
}
