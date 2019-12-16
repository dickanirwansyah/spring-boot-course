package com.jwt.examplejwt.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable {

    @NotBlank(message = "username or email cannot be null")
    private String usernameOrEmail;

    @NotBlank(message = "password cannot be null.")
    private String password;


}
