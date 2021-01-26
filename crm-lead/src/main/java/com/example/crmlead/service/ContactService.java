package com.example.crmlead.service;


import com.example.crmlead.entity.Contact;
import java.util.List;

public interface ContactService {
    void addContact(Contact contact);
    List<Contact> getContact(String agentEmail);
}
