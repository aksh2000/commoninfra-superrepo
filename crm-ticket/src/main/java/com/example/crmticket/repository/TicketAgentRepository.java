package com.example.crmticket.repository;

import com.example.crmticket.entity.TicketAgent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TicketAgentRepository extends CrudRepository<TicketAgent,String> {

    //todo : agent can handle multiple tickets at any given point in time, this should be a list of strings
    @Query(value = "select count(ticket_id) from ticket_agent where agent_email = ?1 ",nativeQuery = true)
    int getTicketId(String agent_email);

    @Query(value = "select ticket_id from ticket_agent where ticket_id = ?1 ",nativeQuery = true)
    String getTicketIdById(String ticket_id);

    //todo : query is not required, can be done with just by the deleteByTicketId should be sufficient
    @Modifying
    @Transactional
    @Query(value = "delete from ticket_agent where ticket_id =?1 ",nativeQuery = true)
    void deleteByTicketId(String ticketId);

    @Query(value = "select agent_email from ticket_agent where ticket_id = ?1",nativeQuery = true)
    String getAgentEmail(String ticket_id);

    List<TicketAgent> findByAgentEmail(String agentEmail);
}
