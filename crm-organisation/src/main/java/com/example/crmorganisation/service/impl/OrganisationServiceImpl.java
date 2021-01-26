package com.example.crmorganisation.service.impl;

import com.example.crmorganisation.Constant;
import com.example.crmorganisation.dto.Admin;
import com.example.crmorganisation.entity.Agent;
import com.example.crmorganisation.entity.Organisation;
import com.example.crmorganisation.repository.OrganisationRepository;
import com.example.crmorganisation.service.OrganisationService;
import dto.PBOrgDTO;
import dto.QuoraOrgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class OrganisationServiceImpl implements OrganisationService
{
    @Autowired
    OrganisationRepository organisationRepository;

    @Override
    @KafkaListener(topics = Constant.KAFKA_TOPIC_FOR_ORGANISATION_PB, groupId = "group_pb")
    public void addOrganisationPb(PBOrgDTO pbOrgDTO)
    {
        Organisation org = new Organisation();
        org.setAdminEmail(pbOrgDTO.getAdminEmail());
        org.setCategory(pbOrgDTO.getCategory());
        organisationRepository.save(org);
    }

    @Override
    @KafkaListener(topics = Constant.KAFKA_TOPIC_FOR_ORGANISATION_QUORA, groupId = "group_pb")
    public void addOrganisationQuora(QuoraOrgDTO quoraOrgDTO)
    {
        Organisation org = new Organisation();
        org.setAdminEmail(quoraOrgDTO.getAdminEmail());
        org.setCategory(quoraOrgDTO.getCategory());
        organisationRepository.save(org);
    }
    @Override
    public String validateAdmin(String adminEmail) {
        return organisationRepository.validateAdmin(adminEmail);
    }

}
