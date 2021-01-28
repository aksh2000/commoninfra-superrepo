package com.cms.quiz.service.implementation;

import com.cms.quiz.dto.LeaderBoardList;
import com.cms.quiz.entity.QuizLeaderBoard;
import com.cms.quiz.repository.QuizLeaderBoardRepository;
import com.cms.quiz.service.IQuizLeaderBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizLeaderBoardServiceImpl implements IQuizLeaderBoard {

    @Autowired
    QuizLeaderBoardRepository quizLeaderBoardRepository;

    @Override
    public int updateLeaderBoard(Long quizId, String userId, double score) {
        return quizLeaderBoardRepository.updateLeaderBoard(quizId,userId,score);
    }

    @Override
    public QuizLeaderBoard addLeaderBoard(QuizLeaderBoard quizLeaderBoard) {
        return quizLeaderBoardRepository.save(quizLeaderBoard);
    }

//    @Override
//    public LeaderBoardList getUserRating(String userId) {
//        return quizLeaderBoardRepository.getUserRating(userId);
//    }
}
