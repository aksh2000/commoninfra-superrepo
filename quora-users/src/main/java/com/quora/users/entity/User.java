package com.quora.users.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "quoraUsers")
public class User {
    @Id
    String userEmail;
    String firstName;
    String lastName;
    boolean isPrivate;
    String associatedBusinessEmail;
    String photoUrl;
}
