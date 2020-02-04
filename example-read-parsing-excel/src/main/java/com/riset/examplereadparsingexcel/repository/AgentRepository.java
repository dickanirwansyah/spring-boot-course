package com.riset.examplereadparsingexcel.repository;


import com.riset.examplereadparsingexcel.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
}
