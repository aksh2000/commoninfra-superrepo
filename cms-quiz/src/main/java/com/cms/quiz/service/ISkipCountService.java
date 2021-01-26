package com.cms.quiz.service;

import com.cms.quiz.entity.SkipCount;
import org.springframework.stereotype.Service;

@Service
public interface ISkipCountService{

    SkipCount save(SkipCount skipCount);

    SkipCount findByQuizIdAndUserId(long quizId, String userId);
}
