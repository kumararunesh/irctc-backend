package com.arunesh.irctc.irctc_backend.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TrainDto {

    @NotEmpty(message = "Train number is required !!")
    @Size(min =3 ,max=20,message = "Invalid Length of Train Number")
    @Pattern(regexp="^\\d+$",message="Train no can only contain numbers")
    private String trainNo ;

    @Pattern(regexp="^[A-Za-z\\s-]+$", message="Only alphabets spaces and hyphens are allowed ")
    private String name;


    private String routeName;



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
