package com.cms.admin.service;

import com.cms.admin.entity.Questions;

import java.util.Optional;

public interface IQuestionService {
    Optional<Questions> getQuestions(Long questionId);

    Questions saveQuestions(Questions questions);
}
