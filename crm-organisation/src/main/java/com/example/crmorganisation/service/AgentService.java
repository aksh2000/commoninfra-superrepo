package com.example.crmorganisation.service;

import com.example.crmorganisation.dto.AgentDTO;
import com.example.crmorganisation.dto.AgentValidateDTO;
import com.example.crmorganisation.entity.Agent;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AgentService
{
    public void addAgent(AgentDTO agentDTO);
    public Agent getAdmin(String agentEmail);
    List<Agent> agentList(String adminEmail);
    void deleteAgent(String agentId);
    String validateAgent(String agentEmail);
}
