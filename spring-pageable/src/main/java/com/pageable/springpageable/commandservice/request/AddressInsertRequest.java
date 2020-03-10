package com.pageable.springpageable.commandservice.request;

import com.pageable.springpageable.commandservice.command.ServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressInsertRequest implements ServiceRequest {

    @NotBlank
    private String street;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String no;

    @NotBlank
    private String village;

    @NotBlank
    private String city;

    @NotBlank
    private String country;
}
