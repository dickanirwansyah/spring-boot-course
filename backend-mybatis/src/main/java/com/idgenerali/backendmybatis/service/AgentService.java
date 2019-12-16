package com.idgenerali.backendmybatis.service;

import com.idgenerali.backendmybatis.model.Agent;

import java.util.ArrayList;

public interface AgentService {
    public ArrayList<Agent> listAllAgent();
    public ArrayList<Agent> listAgentByName(String firstName);
    Agent findByAgentCode(String agentCode);
    ArrayList<Agent> listAgentByStatusTrue();
    ArrayList<Agent> listAgentByStatusFalse();
    boolean saveAgent(Agent agent);
    boolean updateAgent(Agent agent);
}
