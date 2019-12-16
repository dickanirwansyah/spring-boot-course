package com.jwt.examplejwt.service.impl;

import com.jwt.examplejwt.entity.Leads;
import com.jwt.examplejwt.exception.ResourceNotFoundException;
import com.jwt.examplejwt.payload.LeadsRequest;
import com.jwt.examplejwt.repository.LeadsRepository;
import com.jwt.examplejwt.service.LeadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadsServiceImpl implements LeadsService {

    @Autowired
    private LeadsRepository leadsRepository;

    @Override
    public ArrayList<Leads> dataLeads() {
       ArrayList<Leads> leads = new ArrayList<>();
       List<Leads> leadsRepo = leadsRepository.findAll();
       for (Leads lead : leadsRepo){
           leads.add(lead);
       }
       return leads;
    }

    @Override
    public Leads createLeads(LeadsRequest leadsRequest) {
        Leads leads = Leads
                .builder()
                .firstName(leadsRequest.getFirstName())
                .lastName(leadsRequest.getLastName())
                .dob(leadsRequest.getDob())
                .phone(leadsRequest.getPhone())
                .build();
        return leadsRepository.save(leads);
    }

    @Override
    public Leads updateLeads(Long id, LeadsRequest leadsRequest) {
        Leads leads = Leads
                .builder()
                .id(id)
                .firstName(leadsRequest.getFirstName())
                .lastName(leadsRequest.getLastName())
                .dob(leadsRequest.getDob())
                .build();
        return leadsRepository.save(leads);
    }

    @Override
    public Leads findLeadsById(Long id) {
        return leadsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("leads id with "+id+" not found"));
    }

    @Override
    public Leads findLeadsByEmail(String email) {
        return leadsRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("leads email with "+email+" not found"));
    }

    @Override
    public Leads findLeadsByPhone(String phone) {
        return leadsRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("leads phone with "+phone+" not found"));
    }
}
