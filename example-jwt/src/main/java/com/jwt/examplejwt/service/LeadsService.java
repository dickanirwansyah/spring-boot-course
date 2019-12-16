package com.jwt.examplejwt.service;

import com.jwt.examplejwt.entity.Leads;
import com.jwt.examplejwt.payload.LeadsRequest;

import java.util.ArrayList;

public interface LeadsService {
    ArrayList<Leads> dataLeads();
    Leads createLeads(LeadsRequest leadsRequest);
    Leads updateLeads(Long id, LeadsRequest leadsRequest);
    Leads findLeadsById(Long id);
    Leads findLeadsByEmail(String email);
    Leads findLeadsByPhone(String phone);
}
