package com.idgenerali.backendmybatis.service.impl;

import com.idgenerali.backendmybatis.exception.FileStorageException;
import com.idgenerali.backendmybatis.exception.ResourceNotFoundException;
import com.idgenerali.backendmybatis.mapper.UploadFileMapper;
import com.idgenerali.backendmybatis.model.upload.UploadFile;
import com.idgenerali.backendmybatis.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    private static final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Override
    public ArrayList<UploadFile> listUpload() {
        ArrayList<UploadFile> uploadFiles = new ArrayList<>();
        List<UploadFile> filesMappers = uploadFileMapper.listAllUpload();
        for (UploadFile uploadFile : filesMappers){
            uploadFiles.add(uploadFile);
        }
        return uploadFiles;
    }

    @Override
    public boolean storeFile(MultipartFile file) {
        boolean valid = false;
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if (fileName.contains("..")){
                throw new FileStorageException("Sorry ! file name contains invalid path sequence "+fileName);
            }

            UploadFile uploadFile = new UploadFile();
            uploadFile.setId(UUID.randomUUID().toString().substring(20));
            uploadFile.setFileName(fileName);
            uploadFile.setFileType(file.getContentType());
            uploadFile.setFileData(file.getBytes());
            uploadFileMapper.insertUploadFile(uploadFile);
            valid = true;
        }catch (IOException e){
            throw new FileStorageException("could not store file "+fileName+" please try again !", e);
        }
        return valid;
    }

    @Override
    public UploadFile getFile(String fileId) {
        return uploadFileMapper.findUploadById(fileId)
                .orElseThrow(()-> new ResourceNotFoundException("file with id "+fileId+" not found"));
    }

    @Override
    public UploadFile getFileByName(String name) {
        return uploadFileMapper.findUploadByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("file with name "+name+" not found"));
    }
}
