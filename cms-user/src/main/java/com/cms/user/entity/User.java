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
    String userId;
    String firstName;
    String lastName;
    String email;
    String phone;


}



