package com.cms.quiz.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "quizResponses")
public class QuizResponses {
    Long quizId;
    String userId;
    Long questionId;
    String answer;
    double responseTime;
    double questionScore;
}
