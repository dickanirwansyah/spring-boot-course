package com.idgenerali.backendmybatis.service;

import com.idgenerali.backendmybatis.model.upload.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface UploadFileService {
    ArrayList<UploadFile> listUpload();
    boolean storeFile(MultipartFile file);
    UploadFile getFile(String fileId);
    UploadFile getFileByName(String name);
}
