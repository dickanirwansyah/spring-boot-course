package com.pageable.springpageable.commandservice.controller;

import com.pageable.springpageable.commandservice.command.ServiceExecutor;
import com.pageable.springpageable.commandservice.entity.Address;
import com.pageable.springpageable.commandservice.request.AddressInsertRequest;
import com.pageable.springpageable.commandservice.response.Response;
import com.pageable.springpageable.commandservice.service.AddressInserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/address")
public class AddressController {

    @Autowired
    private ServiceExecutor executor;

    //just save no response
    @PostMapping(value = "/save")
    public void justSaveNoResponse(@RequestBody @Valid AddressInsertRequest requestBody){
        executor.execute(AddressInserService.class, requestBody);
    }


}
