package com.cms.admin.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
@Data
public class Admin {
    @Id
    String adminId;
    String firstName;
    String LastName;
    String email;
    String phone;
}