package com.cms.admin.service.implementation;

import com.cms.admin.entity.Questions;
import com.cms.admin.repository.QuestionRepository;
import com.cms.admin.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class QuestionServiceImpl implements IQuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Override
    public Optional<Questions> getQuestions(Long questionId) {
        return questionRepository.findById(questionId);
    }

    @Override
    public Questions saveQuestions(Questions questions) {
        return questionRepository.save(questions);
    }

    @Override
    public List<Questions> findByCategoryId(Long categoryId) {
        return questionRepository.findByCategoryId(categoryId);
    }
}
