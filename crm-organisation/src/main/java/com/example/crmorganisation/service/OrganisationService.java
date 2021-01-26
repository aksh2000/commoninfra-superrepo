package com.example.crmorganisation.service;


import com.example.crmorganisation.dto.Admin;
import com.example.crmorganisation.entity.Agent;
import com.example.crmorganisation.entity.Organisation;
import dto.PBOrgDTO;
import dto.QuoraOrgDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrganisationService
{

    String validateAdmin(String adminEmail);
    void addOrganisationPb(PBOrgDTO pbOrgDTO);
    void addOrganisationQuora(QuoraOrgDTO quoraOrgDTO);


}
