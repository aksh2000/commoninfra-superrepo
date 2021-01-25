package com.cms.quiz.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "leaderboard")
public class QuizLeaderBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long leaderBoardId;
    private Long quizId;
    private String userId;
    private double totalScore;
}
