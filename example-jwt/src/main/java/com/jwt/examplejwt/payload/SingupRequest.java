package com.jwt.examplejwt.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingupRequest implements Serializable {

    /** PAYLOAD SINGUP REQUEST **/

    @NotBlank(message = "name cannot be null")
    @Size(min = 4, max = 40)
    private String requestName;

    @NotBlank(message = "username cannot be null")
    @Size(min = 4, max = 15)
    private String requestUsername;

    @NotBlank(message = "email cannot be null")
    @Email(message = "email not valid")
    private String requestEmail;

    @NotBlank(message = "password cannot be null")
    @Size(min = 4, max = 15)
    private String requestPassword;
}
