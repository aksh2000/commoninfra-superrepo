package com.example.crmlead.repository;

import com.example.crmlead.entity.Lead;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LeadRepository extends CrudRepository<Lead, String> {

    @Query(value = "select * from lead where admin_email= ?1",nativeQuery = true)
    List<Lead> getLead(String adminEmail);

    @Modifying
    @Transactional
    @Query(value = "delete from lead where admin_email= ?2 and lead_email= ?1",nativeQuery = true)
    void deleteLead(String leadEmail, String adminEmail);

    @Query(value = "select database_id from lead where database_id=?1",nativeQuery = true)
    String checkAvailable(String databaseId);

}
