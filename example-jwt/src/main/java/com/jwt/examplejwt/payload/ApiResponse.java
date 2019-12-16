package com.jwt.examplejwt.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse implements Serializable {

    private LocalDateTime timestamp;
    private Boolean success;
    private String message;

}
