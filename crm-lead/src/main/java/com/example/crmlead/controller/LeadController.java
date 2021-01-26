package com.example.crmlead.controller;


import com.example.crmlead.dto.ContactDTO;
import com.example.crmlead.entity.Contact;
import com.example.crmlead.entity.Lead;
import com.example.crmlead.service.ContactService;
import com.example.crmlead.service.LeadService;
import dto.PBLeadDTO;
import dto.PBOrgDTO;
import dto.QuoraLeadDTO;
import dto.QuoraOrgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping(value="/lead")
public class LeadController {

    @Autowired
    LeadService leadService;

    @Autowired
    ContactService contactService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getLead")
    public List<Lead> getLead(@RequestHeader("username") String agentEmail){


        //get admin Email
          String adminEmail = restTemplate.getForObject("http://10.177.1.246:8081/crmorg/find?agentEmail="+agentEmail,String.class);

        //get lead list
        System.out.println(adminEmail);
        String[] abc = adminEmail.split("-",2);
        System.out.println(abc[0]);
        System.out.println(abc[1]);
        if(abc[1].equals("supportAgent")) {System.out.println("hello");
            return leadService.getLead(abc[0]);}
        return null;
    }
    @DeleteMapping("/deleteLead")
    public void deleteLead(@Param("leadEmail") String leadEmail, @Param("adminEmail") String adminEmail){
        leadService.deleteLead(leadEmail,adminEmail);
    }

    @PostMapping("/addContact")
    public void addContact(@RequestBody ContactDTO contactDTO,@RequestHeader("username") String agentEmail){

        //get agent Email from token
      //  String agentEmail=restTemplate.getForObject("http://localhost:8081/product/updateInventoryQuantity?agentToken="+contactDTO.getAgentEmail(),String.class);

        //get admin Email
       // System.out.println();
        String adminEmail = restTemplate.getForObject("http://10.177.1.246:8081/crmorg/find?agentEmail="+agentEmail,String.class);
     //   System.out.println(adminEmail);
        String[] abc = adminEmail.split("-",2);
        Contact contact = new Contact();
        contact.setAddress(contactDTO.getAddress());
        contact.setAdminEmail(abc[0]);
        contact.setLeadEmail(contactDTO.getLeadEmail());
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setSource(contactDTO.getSource());
        contact.setMobileNo(contactDTO.getMobileNo());
        contact.setAgentEmail(agentEmail);

        String available = leadService.checkAvailable(contactDTO.getDatabaseId());
        if(available!=null) {
            contactService.addContact(contact);

            leadService.deleteLead(contactDTO.getLeadEmail(), abc[0]);
        }
    }
    @GetMapping("/getContact")
    public List<Contact> getContact(@RequestHeader("username") String agentEmail){

            return contactService.getContact(agentEmail);

    }
    @PostMapping("/addPbLead")
    public void addTicketPB(@RequestBody PBLeadDTO pbLeadDTO){
        leadService.addLeadPB(pbLeadDTO);
    }
    @PostMapping("/addQuoraLead")
    public void addTicketQuora(@RequestBody QuoraLeadDTO quoraLeadDTO){
        leadService.addLeadQuora(quoraLeadDTO);
    }
}
