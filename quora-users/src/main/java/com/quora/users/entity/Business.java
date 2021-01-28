package com.quora.users.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "quoraBusiness")
public class Business {
    @Id
    String businessEmail;
    String businessName;
    String photoUrl;
}
