package com.cms.quiz.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "leaderboard")
public class QuizLeaderBoard {
    Long quizId;
    String userId;
    double totalScore;
}
