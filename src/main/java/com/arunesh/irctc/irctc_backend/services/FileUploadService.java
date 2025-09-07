package com.arunesh.irctc.irctc_backend.services;

import com.arunesh.irctc.irctc_backend.entities.ImageMetaData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

     ImageMetaData upload(MultipartFile file) throws IOException;

}
