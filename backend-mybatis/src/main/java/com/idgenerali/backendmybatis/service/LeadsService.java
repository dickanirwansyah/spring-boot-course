package com.idgenerali.backendmybatis.service;

import com.idgenerali.backendmybatis.model.HandleLeadsStatus;
import com.idgenerali.backendmybatis.model.Leads;

import java.util.ArrayList;

public interface LeadsService {
    ArrayList<Leads> listLeads();
    ArrayList<HandleLeadsStatus> handleListLeadsStatus();
    HandleLeadsStatus handleLeadsStatusById(int id);
    Leads findById(int leadsId);
    Leads findByPhone(String phone);
    boolean saveLeads(Leads leads);
    boolean updateLeads(Leads leads);
}
