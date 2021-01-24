package com.cms.user.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}



