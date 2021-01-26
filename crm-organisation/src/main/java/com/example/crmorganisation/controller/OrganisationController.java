package com.example.crmorganisation.controller;



import com.example.crmorganisation.dto.AgentDTO;
import com.example.crmorganisation.dto.AgentRegister;
import com.example.crmorganisation.dto.AgentValidateDTO;
import com.example.crmorganisation.entity.Agent;
import com.example.crmorganisation.service.AgentService;
import com.example.crmorganisation.service.OrganisationService;
import dto.PBOrgDTO;
import dto.PBTicketDTO;
import dto.QuoraOrgDTO;
import dto.QuoraTicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/crmorg")
//@CrossOrigin(origins = "*")
public class OrganisationController
{
    @Autowired
    OrganisationService organisationService;

    @Autowired
    AgentService agentService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/find")
    public String getAdminEmail(@Param("agentEmail") String agentEmail)
    {
        Agent agent = agentService.getAdmin(agentEmail);
        return agent.getAdminEmail() + "-" + agent.getAgentType();
    }

    @PostMapping("/addAgent")
    public void addAgent(@RequestBody AgentDTO agentDTO,@RequestHeader("username") String username)
    {
        AgentRegister agentRegister=new AgentRegister();
        agentRegister.setUsername(agentDTO.getAgentEmail());
        agentRegister.setPassword(agentDTO.getPassword());
        agentDTO.setAdminToken(username);
        restTemplate.postForEntity("http://10.177.2.29:9005/auth/register",agentRegister,Object.class);
        agentService.addAgent(agentDTO);
    }

    @GetMapping("/validateAdmin")
    public String validateAdmin(@RequestHeader("username") String username){

        String ava = organisationService.validateAdmin(username);
       // System.out.println(username);
        System.out.println(username);
        if(ava==null){
            return "No user Available";
        }
        else{
            return "Ok";
        }
    }

    @GetMapping("/validateAgent")
    public String validateAgent(@RequestHeader("username") String agentEmail){
        String ava = agentService.validateAgent(agentEmail);
        System.out.println(agentEmail);
        if(ava!=null){
            return "Ok";
        }
        else{
            return "No Agent";
        }
    }

    @GetMapping("/agentDetails")
    public List<Agent> agentList(@RequestHeader("username") String adminEmail){
        //get email from token
        System.out.println(adminEmail);
        return agentService.agentList(adminEmail);
    }
    @DeleteMapping("/deleteAgent/{agentId}")
    public void deleteAgent(@PathVariable("agentId") String agentId){
        agentService.deleteAgent(agentId);
    }


    @PostMapping("/addPbOrg")
    public void addTicketPB(@RequestBody PBOrgDTO pbOrgDTO){
        organisationService.addOrganisationPb(pbOrgDTO);
    }
    @PostMapping("/addQuoraOrg")
    public void addTicketQuora(@RequestBody QuoraOrgDTO quoraOrgDTO){
        organisationService.addOrganisationQuora(quoraOrgDTO);
    }

}