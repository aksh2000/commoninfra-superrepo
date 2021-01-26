package com.example.crmorganisation.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "organisation")
public class Organisation
{
    @Id
    @GeneratedValue(generator = "seq_gen_alias") @GenericGenerator(name="seq_gen_alias",strategy = "uuid2")
    private String businessId;

    private String adminEmail;
    private String category;

    public String getBusinessId()
    {
        return businessId;
    }

    public void setBusinessId(String businessId)
    {
        this.businessId = businessId;
    }

    public String getAdminEmail()
    {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail)
    {
        this.adminEmail = adminEmail;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
}
