package com.dicka.springbootrestupload.controller;

import com.dicka.springbootrestupload.payload.UploadFileResponse;
import com.dicka.springbootrestupload.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(value = "/single-upload")
    public UploadFileResponse uploadFile(@RequestParam("file")MultipartFile file){
        String fileName = this.fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(
                fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping(value = "/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName")String fileName,
                                                 HttpServletRequest request){

        //load file as resource
        Resource resource = this.fileStorageService.loadFileAsResource(fileName);

        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException ex){
            ex.getLocalizedMessage();
        }

        if (contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
