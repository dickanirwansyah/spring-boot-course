package com.idgenerali.backendmybatis.mapper;

import com.idgenerali.backendmybatis.model.upload.UploadFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UploadFileMapper {
    List<UploadFile> listAllUpload();
    Optional<UploadFile> findUploadById(@Param("id")String id);
    Optional<UploadFile> findUploadByName(@Param("fileName")String fileName);
    boolean insertUploadFile(@Param("uploadFile")UploadFile uploadFile);
}
