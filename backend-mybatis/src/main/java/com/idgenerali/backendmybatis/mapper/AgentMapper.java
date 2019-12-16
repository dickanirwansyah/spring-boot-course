package com.idgenerali.backendmybatis.mapper;

import com.idgenerali.backendmybatis.model.Agent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AgentMapper {
    List<Agent> listAgent();
    List<Agent> findAgentByName(@Param("firstName")String firstName);
    Optional<Agent> findAgentById(@Param("agentCode")String agentCode);
    Optional<Agent> findAgentByAgentCode(@Param("agentCode")String agentCode);
    Optional<Agent> findAgentByEmail(@Param("email")String email);
    List<Agent> findAgentByStatusTrue();
    List<Agent> findAgentByStatusFalse();
    boolean saveAgent(@Param("agent")Agent agent);
    boolean updateAgent(@Param("agent")Agent agent);
}
