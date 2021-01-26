package com.example.crmticket.repository;


import com.example.crmticket.entity.TicketHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketHistoryRepository extends CrudRepository<TicketHistory,String> {
}
