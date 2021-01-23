package com.cms.quiz.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "quizQuestions")
public class QuizQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long quizQuestionId;
    Long quizId;
    Long questionId;
}
