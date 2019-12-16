package com.jwt.examplejwt.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeadsRequest implements Serializable {

    @NotBlank(message = "first name cannot be null")
    private String firstName;

    @NotBlank(message = "last name cannot be null")
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "dob cannot be null")
    private Date dob;

    @Email(message = "email not valid")
    private String email;
    private String phone;
}
