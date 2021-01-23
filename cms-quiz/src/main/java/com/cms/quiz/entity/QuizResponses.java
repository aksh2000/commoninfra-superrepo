package com.cms.quiz.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "quizResponses")
public class QuizResponses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long quizResponseId;
    Long quizId;
    String userId;
    Long questionId;
    String answer;
    double responseTime;
    double questionScore;
}
