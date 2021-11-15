package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Content;
import com.example.demo.entity.ResponseMessage;
import com.example.demo.service.FileUpload;

@RestController
public class HomeController {
      
	@Autowired
	private FileUpload fileUpload;
	
	@PostMapping("/upload")
	public ResponseMessage uploadFiles(@RequestParam(name="file") MultipartFile file) throws Exception
	{
     return fileUpload.uploadFile(file);		
	}
	
}
