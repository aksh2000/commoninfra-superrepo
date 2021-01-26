package com.example.crmticket.controller;

import com.example.crmticket.dto.TicketNotification;
import com.example.crmticket.entity.Ticket;
import com.example.crmticket.entity.TicketAgent;
import com.example.crmticket.entity.TicketHistory;
import com.example.crmticket.service.TicketAgentService;
import com.example.crmticket.service.TicketHistoryService;
import com.example.crmticket.service.TicketService;
import dto.PBTicketDTO;
import dto.QuoraTicketDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/ticket")
public class  TicketController {
    @Autowired
    TicketService ticketService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RabbitTemplate generatedTemplate;

    @Autowired
    RabbitTemplate inProgressTemplate;

    @Autowired
    TicketAgentService ticketAgentService;

    @Autowired
    TicketHistoryService ticketHistoryService;

    @GetMapping("/assignTicket")
    public List<Ticket> assignTicket(@RequestHeader("username") String agentEmail) {

        String adminEmail = restTemplate.getForObject("http://10.177.1.246:8081/crmorg/find?agentEmail=" + agentEmail, String.class);
        System.out.println(adminEmail);
        TicketAgent ticketAgent = new TicketAgent();
        System.out.println(agentEmail);
        String[] abc = adminEmail.split("-", 2);

        if (abc[1].equals("supportEngineer")) {




                List<TicketAgent> list = ticketAgentService.getAssignedTicket(agentEmail);
                int already = list.size();
            System.out.println(already);

            List<Ticket>  ticketList =new ArrayList<Ticket>() ;

            //to make list from existing ticket;
                for(int i=0;i< list.size();i++){
                    ticketList.add(ticketService.getTicket(list.get(i).getTicketId()));
                }

                //get new ticket from generated state
                for(int i=list.size(); i<5;i++)
                {
                    Ticket newTicket = new Ticket();
                    newTicket = ticketService.generatedTicket(abc[0]);
                    if(newTicket==null)break;
                    ticketService.updateTicketStatus(newTicket.getTicketId());

                    ticketList.add(newTicket);
                    ticketAgent.setTicketId(newTicket.getTicketId());
                    ticketAgent.setAgentEmail(agentEmail);
                    long now = System.currentTimeMillis();
                      ticketAgent.setTimestamp(new Timestamp(now));
                      ticketAgentService.addAssign(ticketAgent);

                }
            System.out.println(ticketList.size());
                return ticketList;


//                ticketService.updateTicketStatus(newTicket.getTicketId());
//
//                ticketAgent.setTicketId(newTicket.getTicketId());
//                ticketAgent.setAgentEmail(agentEmail);
//                long now = System.currentTimeMillis();
//                ticketAgent.setTimestamp(new Timestamp(now));
//                ticketAgentService.addAssign(ticketAgent);
//                return ticketService.getTicket(newTicket.getTicketId());


            }
        return null;

    }


    @PostMapping("/closeTicket")
    public void closeTicket(@RequestBody TicketHistory ticketHistory, @RequestHeader("username") String agentEmail){

        System.out.println(ticketHistory.getTicketId());
        ticketHistory.setAgentEmail(agentEmail);
        ticketHistoryService.closeTicket(ticketHistory);


        //free support engineer


        ticketService.deleteById(ticketHistory.getTicketId());
        ticketAgentService.deleteById(ticketHistory.getTicketId());
    }

    //Testing
//    @PostMapping("/producer")
//    public String producer(@RequestBody TicketNotification ticketNotification) {
//
//        long now = System.currentTimeMillis();
//        ticketNotification.setTimestamp(now);
//        System.out.println(ticketNotification.getTimestamp());
//        generatedTemplate.convertAndSend(ticketNotification);
//        return "Message sent to the RabbitMQ Successfully";
//    }

    @PostMapping("/addPbTicket")
    public void addTicketPB(@RequestBody PBTicketDTO pbTicketDTO){
        ticketService.addTicketPB(pbTicketDTO);
    }
    @PostMapping("/addQuoraTicket")
    public void addTicketQuora(@RequestBody QuoraTicketDTO pbTicketDTO){
        ticketService.addTicketQuora(pbTicketDTO);
    }
}

