package com.arunesh.irctc.irctc_backend.controllers;

import com.arunesh.irctc.irctc_backend.dto.ErrorResponse;
import com.arunesh.irctc.irctc_backend.dto.PagedResponse;
import com.arunesh.irctc.irctc_backend.dto.TrainDto;
import com.arunesh.irctc.irctc_backend.dto.TrainImageDataWithResource;
import com.arunesh.irctc.irctc_backend.entities.TrainImage;
import com.arunesh.irctc.irctc_backend.services.TrainImageService;
import com.arunesh.irctc.irctc_backend.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private TrainImageService trainImageService;

    @RequestMapping(value="" ,method = RequestMethod.GET)
    public PagedResponse<TrainDto> getAllTrains(
            @RequestParam(value= "page" ,defaultValue = "0") int page,
            @RequestParam(value= "size" ,defaultValue = "10") int size,
            @RequestParam(value= "sortBy" ,defaultValue = "name") String sortBy,
            @RequestParam(value= "sortDir" ,defaultValue = "asc") String sortDir


    )
    {
        return this.trainService.all(page,size,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainDto> getTrain(@PathVariable("id") String trainNo)
    {
        return new ResponseEntity<>(this.trainService.get(trainNo),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TrainDto> addTrain(@Validated @RequestBody TrainDto trainDto)
    {
        return new ResponseEntity<TrainDto>(this.trainService.add(trainDto), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public void deleteTrain(@PathVariable("id") String tranNo)
    {
        this.trainService.delete(tranNo);
    }



    @PostMapping("/{trainNo}/upload-image")
    public ResponseEntity<?> uploadTrainImage(

            @PathVariable String trainNo,
            @RequestParam ("image") MultipartFile image
    ) throws IOException {

        String contentType=image.getContentType() ;
        System.out.println(contentType);

        if(contentType.toLowerCase().startsWith("image/"))
        {
            return new ResponseEntity<>(trainImageService.upload(image,trainNo),HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>
                    (new ErrorResponse("Image not uploaded","403",false),
                          HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{trainNo}/serve-image")
    public ResponseEntity<Resource> serveTrainImage(@PathVariable("trainNo") String trainNo) throws MalformedURLException {
        TrainImageDataWithResource trainImageDataWithResource = trainImageService.loadImageByTrainNo(trainNo);
        TrainImage trainImage = trainImageDataWithResource.trainImage();


        return ResponseEntity.ok().contentType(MediaType.parseMediaType(trainImage.getFileType()))
                .body(trainImageDataWithResource.resource());
    }


}
