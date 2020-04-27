package id.dicka.springexceltojson.controller;

import id.dicka.springexceltojson.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService){
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    public List<Map<String, String>> upload(@RequestParam("file")MultipartFile file) throws Exception{
        return uploadService.upload(file);
    }
}
