package com.idgenerali.backendmybatis.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class Agent implements Serializable {

    @NotBlank(message = "agent code cannot be null")
    private String agentCode;

    @NotBlank(message = "first name cannot be null")
    private String firstName;

    @NotBlank(message = "last name cannot be null")
    private String lastName;

    @NotBlank(message = "email cannot be null")
    private String email;

    @NotBlank(message = "phone number cannot be null")
    private String phoneNumber;
    private String homePhoneNumber;

    @NotBlank(message = "address cannot be null")
    private String addressName;
    private String companyName;
    private boolean status;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date dateOfBirth;
    private Date createdAt;
    private Date updatedAt;

}
