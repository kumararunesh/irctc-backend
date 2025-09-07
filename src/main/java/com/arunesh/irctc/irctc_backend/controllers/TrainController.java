package com.arunesh.irctc.irctc_backend.controllers;

import com.arunesh.irctc.irctc_backend.dto.ErrorResponse;
import com.arunesh.irctc.irctc_backend.entities.ImageMetaData;
import com.arunesh.irctc.irctc_backend.entities.Train;
import com.arunesh.irctc.irctc_backend.services.FileUploadService;
import com.arunesh.irctc.irctc_backend.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/photo")
    public ImageMetaData uploadTrainPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        //process file

        ImageMetaData imageMetaData = fileUploadService.upload(file);
        //using reposiotruy save to DB
        return imageMetaData;

    }

    @RequestMapping(value="" ,method = RequestMethod.GET)
    public List<Train> getAllTrains()
    {
        System.out.println("Inside API");
        return this.trainService.all();
    }

    @GetMapping("/{id}")
    public Train getTrain(@PathVariable("id") String trainNo)
    {
        return this.trainService.get(trainNo);
    }

    @PostMapping
    public ResponseEntity<Train> addTrain(@Validated @RequestBody Train train)
    {
        return new ResponseEntity<Train>(this.trainService.add(train), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public void deleteTrain(@PathVariable("id") String tranNo)
    {
        this.trainService.delete(tranNo);
    }




}
