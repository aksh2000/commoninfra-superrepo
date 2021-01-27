package com.cms.quiz.service.implementation;

import com.cms.quiz.dto.LeaderBoardList;
import com.cms.quiz.dto.User;
import com.cms.quiz.entity.Quiz;
import com.cms.quiz.entity.QuizLeaderBoard;
import com.cms.quiz.methods.Methods;
import com.cms.quiz.repository.QuizLeaderBoardRepository;
import com.cms.quiz.repository.QuizQuestionsRepository;
import com.cms.quiz.repository.QuizRepository;
import com.cms.quiz.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements IQuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    QuizLeaderBoardRepository quizLeaderBoardRepository;

    @Autowired
    QuizQuestionsRepository quizQuestionsRepository;
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Optional<Quiz> findById(Long quizId) {
        return quizRepository.findById(quizId);
    }

    @Override
    public Optional<Quiz> getQuizDetails(Long quizId) {
        return quizRepository.findById(quizId);
    }

    @Override
    public List<Quiz> getQuizListByAdminId(String adminId) {
        return quizRepository.findByAdminId(adminId);
    }

    @Override
    public List<Quiz> getStaticQuiz() {
        Date date = new Date();
        List<Quiz> quizList = quizRepository.getStaticQuiz(date,0);
        return Methods.getValidQuizzes(quizList,quizQuestionsRepository);
    }

    @Override
    public List<Quiz> getDynamicQuiz() {
        Date date = new Date();
        List<Quiz> quizList = quizRepository.getDynamicQuiz(date,1);
        System.out.println("Inside Dynamic  :" + quizList.size());
        return Methods.getValidQuizzes(quizList,quizQuestionsRepository);
    }

    @Override
    public List<LeaderBoardList> getLeaderBoard(Long quizId) {
        List<QuizLeaderBoard> quizLeaderBoards = quizLeaderBoardRepository.findByQuizId(quizId);
        List<LeaderBoardList> leaderBoardLists = new ArrayList<>();
        List<User> users = new ArrayList<>();

        for (QuizLeaderBoard quizLeaderBoard:quizLeaderBoards) {
            User user = restTemplate.getForObject("http://CMS-USER/cmsUser/getUserDetails/"+quizLeaderBoard.getUserId(), User.class);
            LeaderBoardList leaderBoardList = new LeaderBoardList();
            leaderBoardList.setUser(user);
            leaderBoardList.setScore(quizLeaderBoard.getTotalScore());
            leaderBoardLists.add(leaderBoardList);
        }
        return leaderBoardLists;
    }

    @Override
    public Quiz setEndTime(Long quizId) {
        Date date = new Date();
        return quizRepository.setEndTime(date,quizId);
    }
}
