package com.idgenerali.backendmybatis.service;

import com.idgenerali.backendmybatis.model.StatusLeads;

import java.util.ArrayList;

public interface StatusLeadsService {
    ArrayList<StatusLeads> listStatusLeads();
    StatusLeads findById(String statusLeads);
}
