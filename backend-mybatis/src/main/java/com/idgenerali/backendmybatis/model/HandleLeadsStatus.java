package com.idgenerali.backendmybatis.model;

import java.io.Serializable;
import java.util.Date;

public class HandleLeadsStatus implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String phone;
    private StatusLeads statusLeads;

    public HandleLeadsStatus(){}

    public HandleLeadsStatus(int id, String firstName, String lastName, Date dob, String phone, StatusLeads statusLeads) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phone = phone;
        this.statusLeads = statusLeads;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public StatusLeads getStatusLeads() {
        return statusLeads;
    }

    public void setStatusLeads(StatusLeads statusLeads) {
        this.statusLeads = statusLeads;
    }

    @Override
    public String toString() {
        return "HandleLeadsStatus{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", statusLeads=" + statusLeads +
                '}';
    }
}
