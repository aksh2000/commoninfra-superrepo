package com.cms.admin.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "nonScreenedQuestions")
@Data
public class NonScreenedQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;
    private String content;
    private String url;
    private int level = 0;
    private int type;
    private Long categoryId;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String rightAnswer;
    private int noOfCorrectAttempt = 0;
    private int maxTime = 2;
}
