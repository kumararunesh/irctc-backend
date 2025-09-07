package com.arunesh.irctc.irctc_backend.services.impl;

import com.arunesh.irctc.irctc_backend.entities.ImageMetaData;
import com.arunesh.irctc.irctc_backend.services.FileUploadService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class CloudinaryFileUploadServiceImpl implements FileUploadService {
    @Override
    public ImageMetaData upload(MultipartFile file) throws IOException {
        //logic to upload file to cloudinary
        return null;
    }
}
