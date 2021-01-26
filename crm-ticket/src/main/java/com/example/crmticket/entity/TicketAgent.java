package com.example.crmticket.entity;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="ticketAgent")
public class TicketAgent {
    @Id
    private String ticketId;
    private String agentEmail;
    private Timestamp timestamp;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
