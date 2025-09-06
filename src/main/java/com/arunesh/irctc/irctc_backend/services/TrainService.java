package com.arunesh.irctc.irctc_backend.services;

import com.arunesh.irctc.irctc_backend.entities.Train;
import com.arunesh.irctc.irctc_backend.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TrainService {

    List<Train> trains = new ArrayList<>();

    public TrainService()
    {
        trains.add(new Train("1234","DEL-BAN SUP",12));
        trains.add(new Train("4567","PAT-MUM RAJ",20));
    }

    public Train add (Train train)
    {
        trains.add(train);
        return train;
    }

    public List<Train> all()
    {
        return trains;
    }
//    public Train get(String trainNo)
//    {
//        return trains.stream().filter(x->x.getTrainNo().equals(trainNo)).
//                findFirst().orElseThrow(()->new NoSuchElementException("No train found with train no:"+trainNo));
//    }
    public Train get(String trainNo)
    {
        return trains.stream().filter(x->x.getTrainNo().equals(trainNo)).
                findFirst().orElseThrow(()->new ResourceNotFoundException("No train found with train no:"+trainNo));
    }


    public void delete(String trainNo)
    {
       trains = this.trains.stream().filter(x -> !x.getTrainNo().equals(trainNo)).toList();
    }
}
