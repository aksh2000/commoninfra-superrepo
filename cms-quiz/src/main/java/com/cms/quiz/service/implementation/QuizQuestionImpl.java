package com.cms.quiz.service.implementation;

import com.cms.quiz.dto.QuestionDetails;
import com.cms.quiz.entity.QuizQuestions;
import com.cms.quiz.repository.QuizQuestionsRepository;
import com.cms.quiz.service.IQuizQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizQuestionImpl implements IQuizQuestionsService {
    @Autowired
    QuizQuestionsRepository quizQuestionsRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public QuizQuestions addQuizQuestion(QuizQuestions quizQuestions) {
        try {
            return quizQuestionsRepository.save(quizQuestions);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<QuizQuestions> getQuizQuestions(Long quizId) {
        return quizQuestionsRepository.findByQuizId(quizId);
    }

    @Override
    public List<QuizQuestions> deleteQuestion(Long quizId, Long questionId) {
        return quizQuestionsRepository.deleteByQuizIdAndQuestionId(quizId,questionId);
    }

    @Override
    public List<QuestionDetails> getQuizQuestionsWithContent(Long quizId) {
        List<QuestionDetails> questionDetails;
        questionDetails = new ArrayList<>();
        List<QuizQuestions> quizQuestions = quizQuestionsRepository.findByQuizId(quizId);
        for (QuizQuestions quizQuestion: quizQuestions) {
            QuestionDetails questionDetails1 = restTemplate.getForObject("http://localhost:9001/cmsAdmin/getQuestionDetails/"+quizQuestion.getQuestionId(), QuestionDetails.class);
            questionDetails.add(questionDetails1);
        }
        return questionDetails;

    }

    @Override
    public Long countQuizQuestions(Long quizId) {
        return quizQuestionsRepository.countQuestions(quizId);
    }
}
