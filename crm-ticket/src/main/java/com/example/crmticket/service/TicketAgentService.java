package com.example.crmticket.service;

import com.example.crmticket.entity.Ticket;
import com.example.crmticket.entity.TicketAgent;

import java.util.List;

public interface TicketAgentService{
    int getTicketId(String agentEmail);
    void deleteById(String ticketId);
    void addAssign(TicketAgent ticketAgent);
    List<TicketAgent> getAssignedTicket(String agentEmail);

}
