package com.cms.admin.service.implementation;

import com.cms.admin.entity.NonScreenedQuestions;
import com.cms.admin.repository.NonScreenedQuestionsRepository;
import com.cms.admin.service.INonScreenedQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class NonScreenedQuestionsServiceImpl implements INonScreenedQuestionsService {
    @Autowired
    NonScreenedQuestionsRepository nonScreenedQuestionsRepository;

    Optional<NonScreenedQuestions> findFirstElement(){
        return nonScreenedQuestionsRepository.findFirstByOrderByTypeAsc();
    }

    void deleteById(Long questionId){
         nonScreenedQuestionsRepository.deleteById(questionId);
    }
}
