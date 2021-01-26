package com.example.crmlead.service.impl;

import com.example.crmlead.entity.Contact;
import com.example.crmlead.repository.ContactRepository;
import com.example.crmlead.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public void addContact(Contact contact){
        contactRepository.save(contact);
    }

    @Override
    public List<Contact> getContact(String agentEmail){
        return contactRepository.getContact(agentEmail);
    }
}
