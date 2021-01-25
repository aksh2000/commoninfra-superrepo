package com.cms.quiz.service.implementation;

import com.cms.quiz.dto.QuestionDetails;
import com.cms.quiz.entity.QuizQuestions;
import com.cms.quiz.repository.QuizQuestionsRepository;
import com.cms.quiz.service.IQuizQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
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
        System.out.println("Inside Get Quiz Questions!!!");
        for (QuizQuestions quizQuestion: quizQuestions) {
            System.out.println("LL");
            QuestionDetails questionDetails1 = restTemplate.getForObject("http://CMS-ADMIN/cmsAdmin/getQuestionDetails/"+quizQuestion.getQuestionId(), QuestionDetails.class);
            questionDetails.add(questionDetails1);
        }
        return questionDetails;

    }
}
