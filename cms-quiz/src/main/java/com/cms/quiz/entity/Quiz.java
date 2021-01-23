package com.cms.quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "quiz")
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long quizId;
    String adminId;
    String name;
    int type;
    String category;
    int size;
    int duration;
    Date startTime;
    Date endTime;
}
