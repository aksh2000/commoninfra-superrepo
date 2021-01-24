package com.cms.admin.service;

import com.cms.admin.entity.Questions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface IQuestionService {
    Optional<Questions> getQuestions(Long questionId);

    Questions saveQuestions(Questions questions);

    List<Questions> findByCategoryId(Long categoryId);

    List<Questions> getQuestionsByCategory(long categoryId);

    List<Questions> getQuestionsByCategoryAndType(long categoryId, int type);
}
