package com.arunesh.irctc.irctc_backend.services;

import com.arunesh.irctc.irctc_backend.dto.TrainDto;
import com.arunesh.irctc.irctc_backend.entities.Train;
import com.arunesh.irctc.irctc_backend.exceptions.ResourceNotFoundException;
import com.arunesh.irctc.irctc_backend.repositories.TrainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TrainService {


    private TrainRepository trainRepository;

    public ModelMapper modelMapper;

    public TrainService(TrainRepository trainRepository, ModelMapper modelMapper) {
        this.trainRepository = trainRepository;
        this.modelMapper = modelMapper;
    }

    public TrainDto add (TrainDto trainDto)
    {

//        Train train = new Train();
//        train.setName(trainDto.getName());
//        train.setTrainNo(trainDto.getTrainNo());
//        train.setRouteName(trainDto.getRouteName());

        Train train = modelMapper.map(trainDto,Train.class);

        Train savedTrain = this.trainRepository.save(train);

//        TrainDto savedTrainDto =new TrainDto();
//        savedTrainDto.setName(savedTrain.getName());
//        savedTrainDto.setRouteName(savedTrain.getRouteName());
//        savedTrainDto.setTrainNo(savedTrain.getTrainNo());

        TrainDto savedTrainDto = modelMapper.map(train,TrainDto.class);
        return savedTrainDto;
    }

    public Page<TrainDto> all(int page,int size,String sortBy,String sortDir)
    {

        //sorting
        Sort sort = sortBy.trim().toLowerCase().equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Train> trainPage = this.trainRepository.findAll(pageable);
       // List<Train> trainsList =trainPage.getContent();


       return trainPage.map(train->modelMapper.map(train,TrainDto.class));
    }

    public TrainDto get(String trainNo)
    {
        Train train = trainRepository.findById(trainNo)
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with train no. " + trainNo));
        return modelMapper.map(train,TrainDto.class);

       }


    public void delete(String trainNo)
    {
        Train train = trainRepository.findById(trainNo)
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with train no. " + trainNo));

        trainRepository.delete(train);
    }
}
