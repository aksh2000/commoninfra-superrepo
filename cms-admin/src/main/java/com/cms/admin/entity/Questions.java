package com.cms.admin.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@Data
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long questionId;
    String content;
    String url;
    int level;
    int type;
    Long categoryId;
    String optionA;
    String optionB;
    String optionC;
    String rightAnswer;
    int noOfCorrectAttempt;
    int maxTime;
}
