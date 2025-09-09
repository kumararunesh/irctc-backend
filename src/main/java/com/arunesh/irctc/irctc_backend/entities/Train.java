package com.arunesh.irctc.irctc_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="trains")
public class Train {


    @Id
    private String trainNo ;

    private String name;
//    @ValidCoach
//    private int coaches;

    private String routeName;


    @OneToOne(cascade=CascadeType.ALL,orphanRemoval = true)
    private TrainImage trainImage;

    public Train() {
    }

    public Train(String trainNo, String name, String routeNAme) {
        this.trainNo = trainNo;
        this.name = name;
        this.routeName = routeNAme;
    }

    public TrainImage getTrainImage() {
        return trainImage;
    }

    public void setTrainImage(TrainImage trainImage) {
        this.trainImage = trainImage;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
