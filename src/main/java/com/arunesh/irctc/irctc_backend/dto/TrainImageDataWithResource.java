package com.arunesh.irctc.irctc_backend.dto;

import com.arunesh.irctc.irctc_backend.entities.TrainImage;
import org.springframework.core.io.Resource;

public record TrainImageDataWithResource(TrainImage trainImage, Resource resource) {
}
