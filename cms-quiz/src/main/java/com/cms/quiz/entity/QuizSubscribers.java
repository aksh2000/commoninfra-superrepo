package com.cms.quiz.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscribers")
@Data
public class QuizSubscribers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quizSubscriberId;
    private Long quizId;
    private String userId;
    private Date userStartTime;
    private Date userEndTime;
    private int status;

}
