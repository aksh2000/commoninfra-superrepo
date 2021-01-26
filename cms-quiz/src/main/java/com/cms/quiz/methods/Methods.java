package com.cms.quiz.methods;

import com.cms.quiz.dto.QuestionDetails;
import com.cms.quiz.entity.QuizQuestions;
import com.cms.quiz.entity.QuizResponses;

public class Methods {
    public static double getScore(QuizResponses quizResponses, QuestionDetails questionDetails){

        boolean isCorrect = questionDetails.getRightAnswer().equals(quizResponses.getAnswer());
        double score = 0;
        if(isCorrect){
            score = 1/quizResponses.getResponseTime()+questionDetails.getLevel()+1;
        }

        return score;
    }
}
