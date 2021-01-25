package com.cms.admin.service.implementation;

import com.cms.admin.entity.NonScreenedQuestions;
import com.cms.admin.repository.NonScreenedQuestionsRepository;
import com.cms.admin.service.INonScreenedQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NonScreenedQuestionsServiceImpl implements INonScreenedQuestionsService {
    @Autowired
    NonScreenedQuestionsRepository nonScreenedQuestionsRepository;

    public Optional<NonScreenedQuestions> findFirstElement(){
        return nonScreenedQuestionsRepository.findFirstByOrderByTypeAsc();
    }

    public void deleteById(Long questionId){
         nonScreenedQuestionsRepository.deleteById(questionId);
    }
}
