package com.idgenerali.backendmybatis.service.impl;

import com.idgenerali.backendmybatis.exception.ResourceNotFoundException;
import com.idgenerali.backendmybatis.mapper.StatusLeadsMapper;
import com.idgenerali.backendmybatis.model.StatusLeads;
import com.idgenerali.backendmybatis.service.StatusLeadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusLeadsServiceImpl implements StatusLeadsService {

    @Autowired
    private StatusLeadsMapper statusLeadsMapper;

    @Override
    public ArrayList<StatusLeads> listStatusLeads() {
        ArrayList<StatusLeads> statusLeads = new ArrayList<>();
        List<StatusLeads> leadsMapper = statusLeadsMapper.listStatusLeads();
        for (StatusLeads status : leadsMapper){
            statusLeads.add(status);
        }
        return statusLeads;
    }

    @Override
    public StatusLeads findById(String statusLeads) {
        return statusLeadsMapper.findByStatusId(statusLeads)
                .orElseThrow(() -> new ResourceNotFoundException("status leads "+statusLeads+" not found"));
    }
}
