package com.idgenerali.backendmybatis.service.impl;

import com.idgenerali.backendmybatis.exception.ResourceConflictException;
import com.idgenerali.backendmybatis.exception.ResourceNotFoundException;
import com.idgenerali.backendmybatis.mapper.LeadsMapper;
import com.idgenerali.backendmybatis.mapper.StatusLeadsMapper;
import com.idgenerali.backendmybatis.model.HandleLeadsStatus;
import com.idgenerali.backendmybatis.model.Leads;
import com.idgenerali.backendmybatis.model.StatusLeads;
import com.idgenerali.backendmybatis.service.LeadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadsServiceImpl implements LeadsService {

    @Autowired
    private LeadsMapper leadsMapper;

    @Autowired
    private StatusLeadsMapper statusLeadsMapper;

    @Override
    public ArrayList<Leads> listLeads() {
        List<Leads> leads = leadsMapper.listLeads();
        ArrayList<Leads> leadsData = new ArrayList<>();
        for (Leads l : leads){
            leadsData.add(l);
        }
        return leadsData;
    }

    @Override
    public ArrayList<HandleLeadsStatus> handleListLeadsStatus() {
        ArrayList<HandleLeadsStatus> leadsStatuses = new ArrayList<>();
        List<Leads> listLeads = leadsMapper.listLeads();
        for (Leads leads : listLeads){
            HandleLeadsStatus hls = new HandleLeadsStatus();
            StatusLeads statusLeads = statusLeadsMapper
                    .findStatusLeadsById(leads.getStatusLeadsId());
            hls.setId(leads.getId());
            hls.setFirstName(leads.getFirstName());
            hls.setLastName(leads.getLastName());
            hls.setPhone(leads.getPhone());
            hls.setDob(leads.getDob());
            hls.setStatusLeads(statusLeads);
            leadsStatuses.add(hls);
        }
        return leadsStatuses;
    }

    @Override
    public HandleLeadsStatus handleLeadsStatusById(int id) {
        HandleLeadsStatus leadsStatus = null;
        Optional<Leads> validLeadsId = leadsMapper.findById(id);
        if (!validLeadsId.isPresent()){
            throw new ResourceNotFoundException("leads id "+id+" not found");
        }else{
            Leads leads = leadsMapper.findLeadsById(id);
            StatusLeads statusLeads = statusLeadsMapper.findStatusLeadsById(leads.getStatusLeadsId());
            leadsStatus = new HandleLeadsStatus();
            leadsStatus.setId(leads.getId());
            leadsStatus.setFirstName(leads.getFirstName());
            leadsStatus.setLastName(leads.getLastName());
            leadsStatus.setDob(leads.getDob());
            leadsStatus.setPhone(leads.getPhone());
            leadsStatus.setStatusLeads(statusLeads);
        }
        return leadsStatus;
    }

    @Override
    public Leads findById(int leadsId) {
        return leadsMapper.findById(leadsId)
                .orElseThrow(() -> new ResourceNotFoundException("id "+leadsId+" not found"));
    }

    @Override
    public Leads findByPhone(String phone) {
        return leadsMapper.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("phone "+phone+" not found"));
    }

    @Override
    public boolean saveLeads(Leads leads) {

        boolean valid = false;

        Optional<Leads> findPhone = leadsMapper.findByPhone(leads.getPhone());
        Optional<StatusLeads> findStatusLeads = statusLeadsMapper.findByStatusId(leads.getStatusLeadsId());

        if (findPhone.isPresent()){
            throw new ResourceConflictException("phone "+leads.getPhone()+" is existing and available");
        }

        if (!findStatusLeads.isPresent()){
            throw new ResourceNotFoundException("status leads "+leads.getStatusLeadsId()+" not found");
        }

        Leads entityLeads = new Leads();
        entityLeads.setFirstName(leads.getFirstName());
        entityLeads.setDob(leads.getDob());
        entityLeads.setLastName(leads.getLastName());
        entityLeads.setStatusLeadsId(leads.getStatusLeadsId());
        entityLeads.setPhone(leads.getPhone());
        leadsMapper.saveLeads(entityLeads);
        valid = true;
        return valid;
    }

    @Override
    public boolean updateLeads(Leads leads) {
        boolean valid = false;
        Optional<Leads> findPhone = leadsMapper.findByPhone(leads.getPhone());
        Optional<StatusLeads> findStatusLeads = statusLeadsMapper.findByStatusId(leads.getStatusLeadsId());

        if (!findStatusLeads.isPresent()){
            throw new ResourceNotFoundException("status leads "+leads.getStatusLeadsId()+" not found");
        }

        Leads entityLeads = new Leads();
        entityLeads.setId(leads.getId());
        entityLeads.setDob(leads.getDob());
        entityLeads.setLastName(leads.getLastName());
        entityLeads.setFirstName(leads.getFirstName());
        entityLeads.setStatusLeadsId(leads.getStatusLeadsId());
        entityLeads.setPhone(leads.getPhone());
        leadsMapper.updateLeads(entityLeads);
        valid = true;
        return valid;
    }
}
