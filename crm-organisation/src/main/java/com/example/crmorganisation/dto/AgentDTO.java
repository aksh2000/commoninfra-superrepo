package com.example.crmorganisation.dto;

public class AgentDTO
{
    private String agentName;
    private String agentEmail;
    private String password;
    private String agentType;
    private String adminToken;

    public String getAgentName()
    {
        return agentName;
    }

    public void setAgentName(String agentName)
    {
        this.agentName = agentName;
    }

    public String getAgentEmail()
    {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail)
    {
        this.agentEmail = agentEmail;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAgentType()
    {
        return agentType;
    }

    public void setAgentType(String agentType)
    {
        this.agentType = agentType;
    }

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }
}
