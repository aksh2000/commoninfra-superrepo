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
    private String adminId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
