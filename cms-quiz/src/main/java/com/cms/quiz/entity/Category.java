package com.cms.quiz.entity;

import lombok.Data;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.*;

@Entity
@Table(name = "cms_category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String name;
}