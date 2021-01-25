package com.cms.quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "quiz")
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quizId;
    private String adminId;
    private String name;
    private int type;
    private Long  categoryId;
    private int size;
    private int duration;
    private Date startTime;
    private Date endTime;
}
