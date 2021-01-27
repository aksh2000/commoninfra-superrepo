package com.cms.quiz.service;

import com.cms.quiz.dto.LeaderBoardList;
import com.cms.quiz.entity.Quiz;

import java.util.List;
import java.util.Optional;

public interface IQuizService {
    Quiz addQuiz(Quiz quiz);
    Optional<Quiz> findById(Long quizId);
    Optional<Quiz> getQuizDetails(Long quizId);

    List<Quiz> getQuizListByAdminId(String adminId);

    List<Quiz> getStaticQuiz();
//
    List<Quiz> getDynamicQuiz();

    List<LeaderBoardList> getLeaderBoard(Long quizId);

    Quiz setEndTime(Long quizId);
}
