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
    Long quizSubscriberId;
    Long quizId;
    String userId;
    Date userStartTime;
    Date userEndTime;
    int status;

}
