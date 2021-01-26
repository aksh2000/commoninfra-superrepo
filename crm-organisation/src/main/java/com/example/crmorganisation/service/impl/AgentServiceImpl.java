package com.example.crmorganisation.service.impl;

import com.example.crmorganisation.dto.AgentDTO;
import com.example.crmorganisation.dto.AgentValidateDTO;
import com.example.crmorganisation.entity.Agent;
import com.example.crmorganisation.repository.AgentRepository;
import com.example.crmorganisation.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService
{
    @Autowired
    AgentRepository agentRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void addAgent(AgentDTO agentDTO)
    {
        Agent agent = new Agent();
        //agent.setAdminEmail(agentDTO.getAgentEmail());
        agent.setAgentType(agentDTO.getAgentType());
        agent.setAgentEmail(agentDTO.getAgentEmail());
        agent.setName(agentDTO.getAgentName());
      //  String adminEmail = restTemplate.getForObject(""+agentDTO.getAdminToken(),String.class);
        agent.setAdminEmail(agentDTO.getAdminToken());
        agentRepository.save(agent);

        // send pass

    }
    @Override
    public Agent getAdmin(String agentEmail){
       return agentRepository.getAdmin(agentEmail);
    }

    @Override
    public List<Agent> agentList(String adminEmail){
        return agentRepository.agentList(adminEmail);
    }
    public void deleteAgent(String agentId){
         agentRepository.deleteAgent(agentId);
    }

    @Override
    public String validateAgent(String agentEmail){
        return agentRepository.validateAgent(agentEmail);
    }
}
