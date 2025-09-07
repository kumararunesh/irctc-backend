package com.arunesh.irctc.irctc_backend.services.impl;

import com.arunesh.irctc.irctc_backend.entities.ImageMetaData;
import com.arunesh.irctc.irctc_backend.helper.Helper;
import com.arunesh.irctc.irctc_backend.services.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${file.upload.folder}")
    private String folder;

    @Override
    public ImageMetaData upload(MultipartFile file) throws IOException {
        String originalFilename=file.getOriginalFilename();
        System.out.println(originalFilename+" is uploaded succesfullly");
        InputStream inputStream =file.getInputStream();

        if(!Files.exists(Paths.get(folder))){
            System.out.println("Creating Folder");
            Files.createDirectories(Paths.get(folder));
        }
        String fileNameWithPath = Helper.getFileName(folder,originalFilename);
        System.out.println(fileNameWithPath);

        Files.copy(file.getInputStream(),Paths.get(fileNameWithPath), StandardCopyOption.REPLACE_EXISTING);
        ImageMetaData metaData = new ImageMetaData();
        metaData.setFieldId(UUID.randomUUID().toString());
        metaData.setFileName(fileNameWithPath);
        metaData.setFileSize(file.getSize());
        metaData.setContentType(file.getContentType());

        return metaData;
    }
}
