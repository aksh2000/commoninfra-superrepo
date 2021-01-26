package com.cms.quiz.service.implementation;

import com.cms.quiz.entity.SkipCount;
import com.cms.quiz.repository.SkipCountRepository;
import com.cms.quiz.service.ISkipCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkipCountServiceImpl implements ISkipCountService {

    @Autowired
    SkipCountRepository skipCountRepository;


    @Override
    public SkipCount save(SkipCount skipCount) {
        return skipCountRepository.save(skipCount);
    }

    @Override
    public SkipCount findByQuizIdAndUserId(long quizId, String userId) {
        return skipCountRepository.findByQuizIdAndUserId(quizId,userId);
    }
}
