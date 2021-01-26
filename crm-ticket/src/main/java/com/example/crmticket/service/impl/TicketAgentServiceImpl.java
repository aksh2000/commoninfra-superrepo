package com.example.crmticket.service.impl;

import com.example.crmticket.dto.Mail;
import com.example.crmticket.dto.TicketInProgress;
import com.example.crmticket.entity.TicketAgent;
import com.example.crmticket.repository.TicketAgentRepository;
import com.example.crmticket.repository.TicketRepository;
import com.example.crmticket.service.TicketAgentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TicketAgentServiceImpl implements TicketAgentService {
    @Autowired
    TicketAgentRepository ticketAgentRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    RabbitTemplate inProgressTemplate;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public int getTicketId(String agentEmail){
        return ticketAgentRepository.getTicketId(agentEmail);
    }
    @Override
    public void deleteById(String ticketId){
       ticketAgentRepository.deleteByTicketId(ticketId);
    }
    @Override
    public void addAssign(TicketAgent ticketAgent){
         ticketAgentRepository.save(ticketAgent);
        TicketInProgress ticketInProgress  = new TicketInProgress();
        ticketInProgress.setTicketId(ticketAgent.getTicketId());
        ticketInProgress.setCounter(0);
        System.out.println("Adding First Time");
        inProgressTemplate.convertAndSend(ticketInProgress);
        System.out.println("Added First Time");
    }
    public List<TicketAgent> getAssignedTicket(String agentEmail){
        return ticketAgentRepository.findByAgentEmail(agentEmail);
    }


    @RabbitListener(queues = "receiveInProgress")
    public void receivedMessage(TicketInProgress ticketInProgress) {
        System.out.println("Inside dead");
        String already = ticketRepository.getTicketStatus(ticketInProgress.getTicketId());
        System.out.println(already);
        if(already.equals("In progress")) {

            int counter = ticketInProgress.getCounter();
            ticketInProgress.setCounter(++counter);
            System.out.println(counter);
            Mail mail = new Mail();
            if(counter ==1 || counter == 2){
                System.out.println(counter);
                String recipientAdmin = ticketRepository.getAdminEmail(ticketInProgress.getTicketId());
                String recipientUser = ticketRepository.getUserEmail(ticketInProgress.getTicketId());
                String recipientAgent = ticketAgentRepository.getAgentEmail(ticketInProgress.getTicketId());
                System.out.println(recipientAgent);
                mail.setRecipient(recipientAdmin);
                mail.setTopic("notification");
                mail.setContent("fgh");

                //todo : create a hash map of mail templates based on counter and reduce the logic with if else

                try {
                    //todo : move i/p address and port into property file
                    restTemplate.postForEntity("http://10.177.2.29:9020/email/send", mail, Object.class);
                }catch (Exception e){
                    System.out.println("mail Exception");
                }
                mail.setRecipient(recipientUser);
                try {
                    restTemplate.postForEntity("http://10.177.2.29:9020/email/send", mail, Object.class);
                }catch (Exception e){
                    System.out.println("mail Exception");
                }
                mail.setRecipient(recipientAgent);
                try {
                    restTemplate.postForEntity("http://10.177.2.29:9020/email/send", mail, Object.class);
                }catch (Exception e){
                    System.out.println("mail Exception");
                }
            }
            else if(counter == 3 || counter ==4){
                String recipientAdmin = ticketRepository.getAdminEmail(ticketInProgress.getTicketId());
                String recipientUser = ticketRepository.getUserEmail(ticketInProgress.getTicketId());
                String recipientAgent = ticketAgentRepository.getAgentEmail(ticketInProgress.getTicketId());
                mail.setRecipient(recipientAdmin);
                mail.setTopic("");
                mail.setContent("");
                System.out.println(counter);
                try {
                    //move the rest call to a different method and use it across all thees places .. change in logic should be in one place
                    restTemplate.postForEntity("http://10.177.2.29:9020/email/send", mail, Object.class);
                }catch (Exception e){
                    System.out.println("mail Exception");
                }
                mail.setRecipient(recipientUser);
                try {
                    restTemplate.postForEntity("http://10.177.2.29:9020/email/send", mail, Object.class);
                }catch (Exception e){
                    System.out.println("mail Exception");
                }
                mail.setRecipient(recipientAgent);
                try {
                    restTemplate.postForEntity("http://10.177.2.29:9020/email/send", mail, Object.class);
                }catch (Exception e){
                    System.out.println("mail Exception");
                }
            }
            else{
                String recipientAdmin = ticketRepository.getAdminEmail(ticketInProgress.getTicketId());
                String recipientUser = ticketRepository.getUserEmail(ticketInProgress.getTicketId());
                String recipientAgent = ticketAgentRepository.getAgentEmail(ticketInProgress.getTicketId());
                System.out.println(recipientAdmin);
                mail.setRecipient(recipientAdmin);
                mail.setTopic("Notification");
                mail.setContent("dsgs");
                System.out.println(counter);
                try {
                    restTemplate.postForEntity("http://10.177.2.29:9020/email/send", mail, Object.class);
                }catch (Exception e){
                    System.out.println("mail Exception");
                }
                mail.setRecipient(recipientUser);
                 try {
                    restTemplate.postForEntity("http://10.177.2.29:9020/email/send", mail, Object.class);
                }catch (Exception e){
                    System.out.println("mail Exception");
                 }
                mail.setRecipient(recipientAgent);
                try {
                    restTemplate.postForEntity("http://10.177.2.29:9020/email/send", mail, Object.class);
                }catch (Exception e){
                    System.out.println("mail Exception");
                }
            }
            System.out.println("adding in main");
            inProgressTemplate.convertAndSend(ticketInProgress);
            System.out.println("added in ");
        }
    }


}
