package com.example.crmorganisation.repository;

import com.example.crmorganisation.entity.Agent;
import com.example.crmorganisation.entity.Organisation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganisationRepository extends CrudRepository<Organisation, String>
{
    @Query(value = "select admin_email from organisation where admin_email =?1",nativeQuery = true)
    String validateAdmin(String adminEmail);


}
