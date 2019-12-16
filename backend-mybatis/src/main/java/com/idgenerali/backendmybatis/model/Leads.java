package com.idgenerali.backendmybatis.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Leads implements Serializable {

    private int id;

    @NotBlank(message = "first name cannot be null.")
    private String firstName;

    @NotBlank(message = "last name cannot be null.")
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @NotNull(message = "dob cannot be null")
    private Date dob;
    private String phone;

    @NotBlank(message = "status cannot be null.")
    private String statusLeadsId;

    public Leads(){}

    public Leads(@NotBlank(message = "first name cannot be null.") String firstName, @NotBlank(message = "last name cannot be null.") String lastName, @NotNull(message = "dob cannot be null") Date dob, String phone, @NotBlank(message = "status cannot be null.") String statusLeadsId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phone = phone;
        this.statusLeadsId = statusLeadsId;
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

    public String getStatusLeadsId() {
        return statusLeadsId;
    }

    public void setStatusLeadsId(String statusLeadsId) {
        this.statusLeadsId = statusLeadsId;
    }

    @Override
    public String toString() {
        return "Leads{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", statusLeadsId='" + statusLeadsId + '\'' +
                '}';
    }
}
