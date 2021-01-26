package com.example.crmlead.service.impl;

import com.example.crmlead.Constant;
import com.example.crmlead.entity.Lead;
import com.example.crmlead.repository.LeadRepository;
import com.example.crmlead.service.LeadService;
import dto.PBLeadDTO;
import dto.QuoraLeadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadServiceImpl implements LeadService {

    @Autowired
    LeadRepository leadRepository;

    @Override
    @KafkaListener(topics = Constant.KAFKA_TOPIC_FOR_LEAD_PB, groupId = "group_pb")
    public void addLeadPB(PBLeadDTO pbLeadDTO) {
        System.out.println("consuming lead from pb:" + pbLeadDTO.getAdminEmail());

        //mapping of dto with entity
        Lead lead = new Lead();
        lead.setLeadEmail(pbLeadDTO.getUserEmail());
        lead.setAdminEmail(pbLeadDTO.getAdminEmail());
        lead.setFirstName(pbLeadDTO.getFirstName());
        lead.setLastName(pbLeadDTO.getLastName());
        lead.setProfileType(pbLeadDTO.getProfileType());
        lead.setSource("Page Book");

        //save to the databases
        leadRepository.save(lead);
    }

    @Override
    @KafkaListener(topics = Constant.KAFKA_TOPIC_FOR_LEAD_QUORA, groupId = "group_pb")
    public void addLeadQuora(QuoraLeadDTO quoraLeadDTO) {
        System.out.println("consuming lead from pb:" + quoraLeadDTO.getAdminEmail());

        //mapping of dto with entity
        Lead lead = new Lead();
        lead.setLeadEmail(quoraLeadDTO.getUserEmail());
        lead.setAdminEmail(quoraLeadDTO.getAdminEmail());
        lead.setFirstName(quoraLeadDTO.getFirstName());
        lead.setLastName(quoraLeadDTO.getLastName());
        lead.setProfileType(quoraLeadDTO.getProfileType());
        lead.setSource("Quora");

        //save to the databases
        leadRepository.save(lead);
    }

    @Override
    public List<Lead> getLead(String adminEmail) {
        return leadRepository.getLead(adminEmail);
    }

    @Override
    public void deleteLead(String leadEmail, String adminEmail) {
        leadRepository.deleteLead(leadEmail, adminEmail);
    }

    @Override
    public String checkAvailable(String databaseId) {
        return leadRepository.checkAvailable(databaseId);

    }
}







//    @KafkaListener(topics = Constant.KAFKA_TOPIC_FOR_ADD_ITEM, groupId = "group_id")
//    public void consumeAddItemKafka(BookDTO bookDTO)
//    {
//        System.out.println("Consuming add item : "+ bookDTO.getAuthor());
//        ModelMapper modelMapper = new ModelMapper();
//        Product product = modelMapper.map(bookDTO, Product.class);
//        System.out.println("pushing in elasticsearch"+ product);
//        elasticProductService.save(product);
//        System.out.println("consumed product");
//    }

