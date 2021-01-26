package com.example.crmorganisation.repository;

import com.example.crmorganisation.entity.Agent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AgentRepository extends CrudRepository<Agent, String>
{
    @Query(value = "select * from agent where agent_email = ?1",nativeQuery = true)
    Agent getAdmin(String agentEmail);

    @Query(value = "select * from agent where admin_email = ?1", nativeQuery = true)
    List<Agent> agentList(String adminEmail);

    @Modifying
    @Transactional
    @Query(value = "delete from agent where agent_id= ?1",nativeQuery = true)
    void deleteAgent(String agentId);

    @Query(value = "select agent_email from agent where agent_email =?1",nativeQuery = true)
    String validateAgent(String agentEmail);

}
