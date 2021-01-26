package com.example.crmlead.repository;

import com.example.crmlead.entity.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {

    @Query(value = "select * from contact where agent_email= ?1",nativeQuery = true)
    List<Contact> getContact(String agentEmail);
}
