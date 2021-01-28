package com.cms.quiz.service;

import com.cms.quiz.dto.LeaderBoardList;
import com.cms.quiz.entity.QuizLeaderBoard;

public interface IQuizLeaderBoard {

    int updateLeaderBoard(Long quizId, String userId, double score);

    QuizLeaderBoard addLeaderBoard(QuizLeaderBoard quizLeaderBoard);

//    LeaderBoardList getUserRating(String userId);

}
