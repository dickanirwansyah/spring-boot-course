package com.jwt.examplejwt.controller;

import com.jwt.examplejwt.entity.Leads;
import com.jwt.examplejwt.service.LeadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/leads")
public class LeadsController {

    @Autowired
    private LeadsService leadsService;

    @GetMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ArrayList<Leads>> getLeads(){
        ArrayList<Leads> leads = leadsService.dataLeads();
        return new ResponseEntity<>(leads, HttpStatus.OK);
    }
}
