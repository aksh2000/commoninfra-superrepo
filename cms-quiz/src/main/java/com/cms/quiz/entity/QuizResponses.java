package com.cms.quiz.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "quizResponses")
public class QuizResponses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizResponseId;
    private Long quizId;
    private String userId;
    private Long questionId;
    private String answer;
    private double responseTime;
    private double questionScore;
}
