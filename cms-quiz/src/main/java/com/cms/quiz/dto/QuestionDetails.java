package com.cms.quiz.dto;

import lombok.Data;

@Data
public class QuestionDetails {
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
