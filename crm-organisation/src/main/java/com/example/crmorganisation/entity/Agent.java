package com.example.crmorganisation.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agent")
public class Agent
{
    @Id
    @GeneratedValue(generator = "seq_gen_alias") @GenericGenerator(name="seq_gen_alias",strategy = "uuid2")
    private String agentId;

    private String name;
    private String agentEmail;
    private String  adminEmail;
    private String status = "available";
    private String agentType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgentType()
    {
        return agentType;
    }

    public void setAgentType(String agentType)
    {
        this.agentType = agentType;
    }

    public String getAgentId()
    {
        return agentId;
    }

    public void setAgentId(String agentId)
    {
        this.agentId = agentId;
    }

    public String getAgentEmail()
    {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail)
    {
        this.agentEmail = agentEmail;
    }

    public String getAdminEmail()
    {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail)
    {
        this.adminEmail = adminEmail;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
