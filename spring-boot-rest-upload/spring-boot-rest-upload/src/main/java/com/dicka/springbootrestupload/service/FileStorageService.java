package com.dicka.springbootrestupload.service;

import com.dicka.springbootrestupload.exception.FileStorageException;
import com.dicka.springbootrestupload.exception.MyFileNotFoundException;
import com.dicka.springbootrestupload.property.FileStorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new FileStorageException("Could not create the directory where the uploaded files will stored.",
                    e);
        }
    }

    public String storeFile(MultipartFile file){
        //normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{

            //check if the file's  name contain invalid characters
            if (fileName.contains("..")){
                log.error("Sorry ! filename contains invalid path sequence "+fileName);
                throw new FileStorageException("Sorry ! filename contains invalid path sequence "+fileName);
            }

            //copy file to the target location (replacing existing file with same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        }catch (IOException ex){
            log.error("could not store file "+fileName+ " please try again ", ex);
            throw new FileStorageException("could not store file "+fileName+ " please try again ", ex);
        }
    }

    public Resource loadFileAsResource(String fileName){
        try{

            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()){
                return resource;
            }else {
                log.error("file not found "+fileName);
                throw new MyFileNotFoundException("file not found "+fileName);
            }

        }catch (MalformedURLException e){
            log.error("file not found "+fileName);
            throw new MyFileNotFoundException("file not found "+fileName);
        }
    }
}
