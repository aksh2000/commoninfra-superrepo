package com.example.crmlead.service;

import com.example.crmlead.entity.Lead;
import dto.PBLeadDTO;
import dto.QuoraLeadDTO;


import java.util.List;

public interface LeadService {
//    void addLeadPB(PBLeadDTO pbLeadDTO);
//    void addLeadQuora(QuoraLeadDTO quoraLeadDTO);
    List<Lead> getLead(String adminEmail);
    void deleteLead(String leadEmail , String adminEmail);
    String checkAvailable(String databaseId);
    void addLeadPB(PBLeadDTO pbLeadDTO);
    void addLeadQuora(QuoraLeadDTO quoraLeadDTO);
}
