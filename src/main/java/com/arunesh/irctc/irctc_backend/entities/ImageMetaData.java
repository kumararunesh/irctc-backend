package com.arunesh.irctc.irctc_backend.entities;

import java.time.LocalDateTime;

public class ImageMetaData {

    private String fileName;
    private String fieldId;
    private String contentType;
    private long fileSize;
    private LocalDateTime uploadedAt;

    public ImageMetaData() {
    }

    public ImageMetaData(String fileName, String fieldId, String contentType, long fileSize, LocalDateTime uploadedAt) {
        this.fileName = fileName;
        this.fieldId = fieldId;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.uploadedAt = uploadedAt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
