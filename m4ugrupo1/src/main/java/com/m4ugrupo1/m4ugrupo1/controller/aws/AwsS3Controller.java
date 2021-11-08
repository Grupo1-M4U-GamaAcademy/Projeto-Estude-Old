package com.m4ugrupo1.m4ugrupo1.controller.aws;


import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.m4ugrupo1.m4ugrupo1.service.aws.AmazonS3ClientService;

@RestController
@RequestMapping("/s3")
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@ResponseBody
public class AwsS3Controller {
	
	private static final String MESSAGE_1 = "Uploaded the file successfully";
    private static final String FILE_NAME = "fileName";

    
    AmazonS3ClientService amazonS3ClientService;

    @GetMapping
    public ResponseEntity<Object> findByName(@RequestBody(required = false) Map<String, String> params) {
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.noCache())
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + params.get(FILE_NAME) + "\"")
                .body(new InputStreamResource(amazonS3ClientService.findByName(params.get(FILE_NAME))));

    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestParam("file") MultipartFile multipartFile) {
    	amazonS3ClientService.save(multipartFile);
        return new ResponseEntity<>(MESSAGE_1, HttpStatus.OK);
    }

}