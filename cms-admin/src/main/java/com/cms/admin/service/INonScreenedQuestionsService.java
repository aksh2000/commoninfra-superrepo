package com.cms.admin.service;

import com.cms.admin.entity.NonScreenedQuestions;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface INonScreenedQuestionsService {

    Optional<NonScreenedQuestions> findFirstElement();

    void deleteById(Long questionId);
    public Long getCountOfNonScreenedQuestions() ;
}
