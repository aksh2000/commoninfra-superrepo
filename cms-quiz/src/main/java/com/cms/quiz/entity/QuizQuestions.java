package com.cms.quiz.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "quizQuestions")
public class QuizQuestions {

    Long quizId;
    Long questionId;
}
