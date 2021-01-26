package com.cms.quiz.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}



