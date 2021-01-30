package com.cms.quiz.methods;

import com.cms.quiz.dto.QuestionDetails;
import com.cms.quiz.entity.Quiz;
import com.cms.quiz.entity.QuizQuestions;
import com.cms.quiz.entity.QuizResponses;
import com.cms.quiz.repository.QuizQuestionsRepository;

import java.util.ArrayList;
import java.util.List;

public class Methods {
    public static double getScore(QuizResponses quizResponses, QuestionDetails questionDetails){

        boolean isCorrect = questionDetails.getRightAnswer().equals(quizResponses.getAnswer());
        double score = 0;
        if(isCorrect){
            score = 1/(quizResponses.getResponseTime()+0.1)+questionDetails.getLevel()+1;
        }
        System.out.println(quizResponses.getQuizId()+ " User's Answer= " + quizResponses.getAnswer()+ "Correct Answer= "+ questionDetails.getRightAnswer());
        System.out.println(quizResponses.getResponseTime() + " isCorrect= "+ isCorrect + " Score =  "+ score);

        return score;
    }

    public static List<Quiz> getValidQuizzes(List<Quiz> quizList, QuizQuestionsRepository quizQuestionsRepository){
        List<Quiz> validQuizzes = new ArrayList<>();
        for (Quiz quiz: quizList) {
            if(quizQuestionsRepository.countQuestions(quiz.getQuizId())== quiz.getSize()){
                validQuizzes.add(quiz);
            }
        }
        return validQuizzes;
    }
}
