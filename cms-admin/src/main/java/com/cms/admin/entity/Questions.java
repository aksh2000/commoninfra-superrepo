package com.cms.admin.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@Data
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;
    private String content;
    private String url;
    private int level;
    private int type;
    private Long categoryId;
    private String optionA;
    private String optionB;
    private String optionC;
    private String rightAnswer;
    private int noOfCorrectAttempt;
    private int maxTime;
}
