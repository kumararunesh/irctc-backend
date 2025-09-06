package com.arunesh.irctc.irctc_backend.controllers;

import com.arunesh.irctc.irctc_backend.dto.ErrorResponse;
import com.arunesh.irctc.irctc_backend.entities.Train;
import com.arunesh.irctc.irctc_backend.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @RequestMapping(value="" ,method = RequestMethod.GET)
    public List<Train> getAllTrains()
    {
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
