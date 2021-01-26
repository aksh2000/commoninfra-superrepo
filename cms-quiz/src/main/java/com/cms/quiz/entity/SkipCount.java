package com.cms.quiz.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "skipCount")
public class SkipCount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long skipCountId;
    private long quizId;
    private String userId;
    private int count;
}
