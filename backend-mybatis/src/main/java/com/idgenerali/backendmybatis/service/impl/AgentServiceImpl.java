package com.idgenerali.backendmybatis.service.impl;

import com.idgenerali.backendmybatis.exception.ResourceConflictException;
import com.idgenerali.backendmybatis.exception.ResourceNotFoundException;
import com.idgenerali.backendmybatis.mapper.AgentMapper;
import com.idgenerali.backendmybatis.model.Agent;
import com.idgenerali.backendmybatis.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    public AgentMapper agentMapper;

    @Override
    public ArrayList<Agent> listAllAgent() {
        ArrayList<Agent> agents = new ArrayList<>();
        List<Agent> agentMappers = agentMapper.listAgent();

        for (Agent agent : agentMappers){
            agents.add(agent);
        }

        if (agents.isEmpty() && agents == null){
            throw new ResourceNotFoundException("sorry data agent is null");
        }

        return agents;
    }

    @Override
    public ArrayList<Agent> listAgentByName(String firstName) {
        ArrayList<Agent> agents = new ArrayList<>();
        List<Agent> agentMappers = agentMapper.findAgentByName(firstName);

        for (Agent agent : agentMappers){
            agents.add(agent);
        }

        if (agents.isEmpty() && agents == null){
            throw new ResourceNotFoundException("sorry data agent with name "+firstName+" not found");
        }

        return agents;
    }

    @Override
    public Agent findByAgentCode(String agentCode) {
        return agentMapper.findAgentByAgentCode(agentCode)
                .orElseThrow(() -> new ResourceNotFoundException("agent code with "+agentCode+" not found"));
    }

    @Override
    public ArrayList<Agent> listAgentByStatusTrue() {
        ArrayList<Agent> agents = new ArrayList<>();
        List<Agent> agentMapers = agentMapper.findAgentByStatusTrue();
        for (Agent agent : agentMapers){
            agents.add(agent);
        }

        if (agents.isEmpty() || agents == null){
            throw new ResourceNotFoundException("agent with status available not found");
        }

        return agents;
    }

    @Override
    public ArrayList<Agent> listAgentByStatusFalse() {
        ArrayList<Agent> agents = new ArrayList<>();
        List<Agent> agentMappers = agentMapper.findAgentByStatusFalse();
        for (Agent agent : agentMappers){
            agents.add(agent);
        }

        if (agents.isEmpty() || agents == null){
            throw new ResourceNotFoundException("agent with status unavailable not found");
        }

        return agents;
    }

    @Override
    public boolean saveAgent(Agent agent) {
        boolean valid = false;
        Optional<Agent> validAgentCode = agentMapper.findAgentByAgentCode(agent.getAgentCode());
        Optional<Agent> validAgentEmail = agentMapper.findAgentByEmail(agent.getEmail());

        if (validAgentCode.isPresent()){
            throw new ResourceConflictException("agent code with "+agent.getAgentCode()+" is existing");
        }

        if (validAgentEmail.isPresent()){
            throw new ResourceConflictException("agent email with "+agent.getEmail()+" is existing");
        }

        Agent entityAgent = new Agent();
        entityAgent.setAgentCode(agent.getAgentCode());
        entityAgent.setFirstName(agent.getFirstName());
        entityAgent.setLastName(agent.getLastName());
        entityAgent.setEmail(agent.getEmail());
        entityAgent.setPhoneNumber(agent.getPhoneNumber());
        entityAgent.setHomePhoneNumber(agent.getHomePhoneNumber());
        entityAgent.setAddressName(agent.getAddressName());
        entityAgent.setCompanyName(agent.getCompanyName());
        entityAgent.setStatus(agent.isStatus());
        entityAgent.setDateOfBirth(agent.getDateOfBirth());
        entityAgent.setCreatedAt(new Date());
        entityAgent.setUpdatedAt(new Date());
        agentMapper.saveAgent(entityAgent);
        valid = true;

        return valid;
    }

    @Override
    public boolean updateAgent(Agent agent) {
        boolean valid = false;
        Optional<Agent> validEmail = agentMapper.findAgentByEmail(agent.getEmail());
        if (validEmail.isPresent()){
            throw new ResourceConflictException("agent email with "+agent.getEmail()+" is existing.");
        }

        Agent entityAgent = new Agent();
        entityAgent.setAgentCode(agent.getAgentCode());
        entityAgent.setFirstName(agent.getFirstName());
        entityAgent.setLastName(agent.getLastName());
        entityAgent.setEmail(agent.getEmail());
        entityAgent.setPhoneNumber(agent.getPhoneNumber());
        entityAgent.setHomePhoneNumber(agent.getHomePhoneNumber());
        entityAgent.setAddressName(agent.getAddressName());
        entityAgent.setCompanyName(agent.getCompanyName());
        entityAgent.setStatus(agent.isStatus());
        entityAgent.setDateOfBirth(agent.getDateOfBirth());
        entityAgent.setCreatedAt(new Date());
        entityAgent.setUpdatedAt(new Date());
        agentMapper.updateAgent(entityAgent);
        valid = true;
        return valid;
    }
}
