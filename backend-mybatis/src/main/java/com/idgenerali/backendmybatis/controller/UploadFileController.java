package com.idgenerali.backendmybatis.controller;

import com.idgenerali.backendmybatis.model.upload.UploadFile;
import com.idgenerali.backendmybatis.payload.UploadFileResponse;
import com.idgenerali.backendmybatis.service.UploadFileService;
import com.idgenerali.backendmybatis.util.ResponseApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/upload-file")
public class UploadFileController {

    private static final Logger log = LoggerFactory.getLogger(UploadFileController.class);

    @Autowired
    private UploadFileService uploadFileService;

    @PostMapping(value = "/upload")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file){

        boolean validSave = uploadFileService.storeFile(file);

        UploadFile uploadFile = null;

        if (validSave == true){
            uploadFile = uploadFileService.getFileByName(file.getOriginalFilename());
        }

        /** download uri **/
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/upload-file/upload/")
                .path(uploadFile.getFileName())
                .toUriString();

        UploadFileResponse response = new UploadFileResponse();
        response.setFileId(uploadFile.getId());
        response.setFileDownloadUri(fileDownloadUri);
        response.setFileName(uploadFile.getFileName());
        response.setFileType(file.getContentType());
        response.setSize(file.getSize());
        return response;
    }

    @PostMapping(value = "/upload-multiples")
    public List<UploadFileResponse> uploadMultipleFile(@RequestParam("file") MultipartFile[] files){
        return Arrays.asList(files)
                .stream()
                .map(file -> this.uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "")
    public ResponseEntity<ResponseApi<ArrayList<UploadFile>>> getFiles(){
        ResponseApi responseApi = new ResponseApi();
        ArrayList<UploadFile> uploadFiles = uploadFileService.listUpload();
        responseApi.setData(uploadFiles);
        responseApi.setStatus(HttpStatus.OK.value());
        responseApi.setMessages("success");
        responseApi.setTimestamp(new Date());
        return ResponseEntity.ok().body(responseApi);
    }
}
