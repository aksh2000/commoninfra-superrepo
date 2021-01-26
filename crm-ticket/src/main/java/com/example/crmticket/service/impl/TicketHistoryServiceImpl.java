package com.example.crmticket.service.impl;

import com.example.crmticket.entity.TicketHistory;
import com.example.crmticket.repository.TicketHistoryRepository;
import com.example.crmticket.service.TicketHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketHistoryServiceImpl implements TicketHistoryService {
    @Autowired
    TicketHistoryRepository ticketHistoryRepository;

    public void  closeTicket(TicketHistory ticketHistory){
        ticketHistoryRepository.save(ticketHistory);
    }
}
