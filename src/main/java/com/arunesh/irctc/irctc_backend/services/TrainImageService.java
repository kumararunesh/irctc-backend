package com.arunesh.irctc.irctc_backend.services;

import com.arunesh.irctc.irctc_backend.config.AppConstants;
import com.arunesh.irctc.irctc_backend.dto.TrainImageDataWithResource;
import com.arunesh.irctc.irctc_backend.dto.TrainImageResponse;
import com.arunesh.irctc.irctc_backend.entities.Train;
import com.arunesh.irctc.irctc_backend.entities.TrainImage;
import com.arunesh.irctc.irctc_backend.exceptions.ResourceNotFoundException;
import com.arunesh.irctc.irctc_backend.repositories.TrainImageRepository;
import com.arunesh.irctc.irctc_backend.repositories.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class TrainImageService {

    @Value("${train.image.folder.path}")
    private String folderPath;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private TrainImageRepository trainImageRepository;

    public TrainImageResponse upload(MultipartFile file,String trainNo) throws IOException {

        Train train = trainRepository.findById(trainNo)
                .orElseThrow(() -> new ResourceNotFoundException("Train not foubnd with train no " + trainNo));

        if(!Files.exists(Paths.get(folderPath)))
        {
            System.out.println("creating folder");
            Files.createDirectories(Paths.get(folderPath));
        }

        String fullFilePath=folderPath+ UUID.randomUUID()+"_"+file.getOriginalFilename();
        Files.copy(file.getInputStream(),Paths.get(fullFilePath), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("fileUploaded");

        TrainImage trainImage = new TrainImage();
        trainImage.setFileName(fullFilePath);
        trainImage.setFileType(file.getContentType());
        trainImage.setSize(file.getSize());

        train.setTrainImage(trainImage);
        trainImage.setTrain(train);


        Train savedTrain = trainRepository.save(train);

        return TrainImageResponse.from(savedTrain.getTrainImage(), AppConstants.BASE_URL,savedTrain.getTrainNo());


    }

    public TrainImageDataWithResource loadImageByTrainNo(String trainNo) throws MalformedURLException {
        Train train = trainRepository.findById(trainNo)
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with id " + trainNo));

        TrainImage trainImage= train.getTrainImage();

        if(trainImage ==null)
        {
            throw new ResourceNotFoundException("Train Image not found");
        }

        Path trainImagePath = Paths.get(trainImage.getFileName());
        if(!Files.exists(trainImagePath))
        {
            throw new ResourceNotFoundException("Train Image not found");

        }

        UrlResource urlResource = new UrlResource(trainImagePath.toUri());

        return new TrainImageDataWithResource(trainImage,urlResource);

    }


}
