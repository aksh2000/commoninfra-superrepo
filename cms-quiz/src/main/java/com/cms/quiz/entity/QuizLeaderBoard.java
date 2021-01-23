package com.cms.quiz.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "leaderboard")
public class QuizLeaderBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long leaderBoardId;
    Long quizId;
    String userId;
    double totalScore;
}
