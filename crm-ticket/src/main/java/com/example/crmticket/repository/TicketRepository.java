package com.example.crmticket.repository;

import com.example.crmticket.entity.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//todo : more methods mean, more logic which in turn means more testing and more code maintenance ...
//instead just follow single method and take fields required for each logic, when you are dealing with single object/ single record
@Repository
public interface TicketRepository extends CrudRepository<Ticket,String> {

    //todo : user findBy query instead of developing a query for this
    @Query(value = "select * from ticket where ticket_id=?1",nativeQuery = true)
    Ticket getTicket(String ticketId);

    //todo : remove query
    @Modifying
    @Transactional
    @Query(value = "delete from ticket where ticket_id =?1 ",nativeQuery = true)
    void deleteByTicketId(String ticketId);

    @Modifying
    @Transactional
    @Query(value = "update ticket set ticket_status= ?2 where ticket_id= ?1",nativeQuery = true)
    void updateTicketStatus(String ticketId , String s);

    //todo : change this to findByAdminEmailAndTicketStatus
    @Query(value = "select * from ticket where admin_email= ?1 and ticket_status= ?2",nativeQuery = true)
    List<Ticket> generatedTicket(String adminEmail, String s);

    @Query(value = "select ticket_status from ticket where ticket_id= ?1",nativeQuery = true)
    String getTicketStatus(String ticketId);

    @Query(value = "select admin_email from ticket where ticket_id = ?1",nativeQuery = true)
    String getAdminEmail(String ticketId);


    @Query(value = "select user_email from ticket where ticket_id = ?1",nativeQuery = true)
    String getUserEmail(String ticketId);
}
