package com.example.crmlead.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String leadId;
    private String leadEmail;
    private String firstName;
    private String lastName;
    private String adminEmail;
    private String source;
    private String mobileNo;
    private String address;
    private String agentEmail;

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getLeadEmail() {
        return leadEmail;
    }

    public void setLeadEmail(String leadEmail) {
        this.leadEmail = leadEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdminEmail() { return adminEmail; }

    public void setAdminEmail(String adminEmail) { this.adminEmail = adminEmail; }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMobileNo() { return mobileNo; }

    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }
}
