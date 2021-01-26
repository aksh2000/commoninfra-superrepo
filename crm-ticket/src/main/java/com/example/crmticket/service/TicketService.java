package com.example.crmticket.service;

import com.example.crmticket.entity.Ticket;
import dto.PBTicketDTO;
import dto.QuoraTicketDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketService {

    Ticket getTicket(String ticketId);
    void deleteById(String ticketId);
    void updateTicketStatus(String ticketId);
    Ticket generatedTicket(String adminEmail);
    String getTicketStatus(String ticketId);
    void addTicketPB(PBTicketDTO pbTicketDTO);
    void addTicketQuora(QuoraTicketDTO quoraTicketDTO);

}
