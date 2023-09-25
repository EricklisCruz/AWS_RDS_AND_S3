package com.awsstudies.estudos.controllers;

import com.awsstudies.estudos.services.S3Services;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/aws")
public class S3Controller {

    private final S3Services s3Services;

    public S3Controller(S3Services s3Services) {
        this.s3Services = s3Services;
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        return s3Services.saveFile(file);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<String>> findAllFiles(){
        List<String> listOfFiles = s3Services.listAllFiles();
        return ResponseEntity.ok().body(listOfFiles);
    }

}
