package com.idgenerali.backendmybatis.mapper;

import com.idgenerali.backendmybatis.model.StatusLeads;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StatusLeadsMapper {
    Optional<StatusLeads> findByStatusId(@Param("statusLeads")String statusLeads);
    StatusLeads findStatusLeadsById(@Param("statusLeads")String statusLeads);
    List<StatusLeads> listStatusLeads();
}
