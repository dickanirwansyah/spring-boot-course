package com.idgenerali.backendmybatis.mapper;

import com.idgenerali.backendmybatis.model.Leads;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LeadsMapper {
    List<Leads> listLeads();
//    Optional<Leads> findByLeadsId(@Param("id")int id);
    boolean saveLeads(@Param("leads")Leads leads);
    boolean updateLeads(@Param("leads")Leads leads);
    Optional<Leads> findByPhone(@Param("phone")String phone);
    Optional<Leads> findById(@Param("id")int id);
    Leads findLeadsById(@Param("id")int id);
}
