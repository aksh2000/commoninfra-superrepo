package com.example.crmticket.service.impl;


import com.example.crmticket.Constant;
import com.example.crmticket.dto.Mail;
import com.example.crmticket.dto.TicketNotification;
import com.example.crmticket.entity.Ticket;
import com.example.crmticket.repository.TicketRepository;
import com.example.crmticket.service.TicketService;
import dto.PBTicketDTO;
import dto.QuoraTicketDTO;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    RabbitTemplate generatedTemplate;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Ticket getTicket(String ticketId){
        return ticketRepository.getTicket(ticketId);
    }

    @Override
    public void deleteById(String ticketId){
        ticketRepository.deleteByTicketId(ticketId);
    }

    @Override
    public void updateTicketStatus(String ticketId){
        ticketRepository.updateTicketStatus(ticketId,"In progress");
    }

    @Override
    public Ticket generatedTicket(String adminEmail){
        List<Ticket> list = ticketRepository.generatedTicket(adminEmail,"Generated");
        System.out.println(list.get(0).getTicketId());
        if(list.size()>0)
        return list.get(0);
        else return null;

    }
    @Override
    public String getTicketStatus(String ticketId){
        return ticketRepository.getTicketStatus(ticketId);
    }

    // Email Notification for Generated State User


    @RabbitListener(queues = "receiveGenerated")
    public void receivedMessage(TicketNotification ticketNotification) {
        String ticketStatus = ticketRepository.getTicketStatus(ticketNotification.getTicketId());
        if(ticketStatus.equals("Generated")) {
            int counter = ticketNotification.getCounter();
            ticketNotification.setCounter(counter++);
            Mail mail = new Mail();
            System.out.println(ticketNotification.getTicketId());

            if(counter ==1 || counter == 2){
                //send Email
                String recipientAdmin = ticketRepository.getAdminEmail(ticketNotification.getTicketId());
                String recipientUser = ticketRepository.getUserEmail(ticketNotification.getTicketId());
                mail.setRecipient(recipientAdmin);
                mail.setTopic("");
                mail.setContent("");
                restTemplate.postForEntity("http://10.177.2.29:9020/email/send",mail,Object.class);
                mail.setRecipient(recipientUser);
                restTemplate.postForEntity("http://10.177.2.29:9020/email/send",mail,Object.class);
            }
            else if(counter == 3 || counter ==4){
                //send Email
                String recipientAdmin = ticketRepository.getAdminEmail(ticketNotification.getTicketId());
                String recipientUser = ticketRepository.getUserEmail(ticketNotification.getTicketId());
                mail.setRecipient(recipientAdmin);
                mail.setTopic("");
                mail.setContent("");
                restTemplate.postForEntity("http://10.177.2.29:9020/email/send",mail,Object.class);
                mail.setRecipient(recipientUser);
                restTemplate.postForEntity("http://10.177.2.29:9020/email/send",mail,Object.class);
            }
            else{
                //send Email
                String recipientAdmin = ticketRepository.getAdminEmail(ticketNotification.getTicketId());
                String recipientUser = ticketRepository.getUserEmail(ticketNotification.getTicketId());
                mail.setRecipient(recipientAdmin);
                mail.setTopic("");
                mail.setContent("");
                restTemplate.postForEntity("http://10.177.2.29:9020/email/send",mail,Object.class);
                mail.setRecipient(recipientUser);
                restTemplate.postForEntity("http://10.177.2.29:9020/email/send",mail,Object.class);
            }

            long now = System.currentTimeMillis();
            ticketNotification.setTimestamp(now);
            generatedTemplate.convertAndSend( ticketNotification);
        }
    }

    //todo kafka listener enable it

    @KafkaListener(topics = Constant.KAFKA_TOPIC_FOR_TICKET_PB,groupId = "group_pb")
    @Override
    public void addTicketPB(PBTicketDTO pbTicketDTO){

        //mapping dto with object
        Ticket ticket = new Ticket();

        ticket.setAdminEmail(pbTicketDTO.getAdminEmail());
        ticket.setTicketStatus("Generated");
        long now = System.currentTimeMillis();
        ticket.setTimestamp(new Timestamp(now));
        ticket.setFirstName(pbTicketDTO.getFirstName());
        ticket.setLastName(pbTicketDTO.getLastName());
        ticket.setUserEmail(pbTicketDTO.getUserEmail());
        ticket.setPostId(pbTicketDTO.getPostId());
        ticket.setProfileType(pbTicketDTO.getProfileType());
        ticket.setSource("Page Book");

        ticketRepository.save(ticket);
        TicketNotification ticketNotification = new TicketNotification();
        ticketNotification.setTicketId(ticket.getTicketId());
        ticketNotification.setTimestamp(123455667);
        ticketNotification.setCounter(0);

        generatedTemplate.convertAndSend(ticketNotification);

    }
    @Override
    @KafkaListener(topics = Constant.KAFKA_TOPIC_FOR_TICKET_QUORA,groupId = "group_pb")
    public void addTicketQuora(QuoraTicketDTO quoraTicketDTO){

        //mapping dto with object
        Ticket ticket = new Ticket();

        ticket.setAdminEmail(quoraTicketDTO.getAdminEmail());
        ticket.setTicketStatus("Generated");
        long now = System.currentTimeMillis();
        ticket.setTimestamp(new Timestamp(now));
        ticket.setFirstName(quoraTicketDTO.getFirstName());
        ticket.setLastName(quoraTicketDTO.getLastName());
        ticket.setUserEmail(quoraTicketDTO.getUserEmail());
        ticket.setPostId(quoraTicketDTO.getPostId());
        ticket.setProfileType(quoraTicketDTO.getProfileType());
        ticket.setSource("Quora");

        ticketRepository.save(ticket);
        TicketNotification ticketNotification = new TicketNotification();
        ticketNotification.setTicketId(ticket.getTicketId());
        ticketNotification.setTimestamp(123455667);
        ticketNotification.setCounter(0);
        generatedTemplate.convertAndSend(ticketNotification);
    }

}
