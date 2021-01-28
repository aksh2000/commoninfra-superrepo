package com.quora.users.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "quoraEngagement")
public class Engagement {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String engagementId;
    String userBusinessEmail;
    String secondaryEmail;
    boolean isApproved;
}
