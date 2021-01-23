package com.cms.quiz.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "subscribers")
@Data
public class QuizSubscribers {
    Long quizId;
    String userId;
    Date userStartTime;
    Date userEndTime;
    int status;


}
