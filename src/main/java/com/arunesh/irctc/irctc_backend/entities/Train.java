package com.arunesh.irctc.irctc_backend.entities;

import com.arunesh.irctc.irctc_backend.annotations.ValidCoach;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Train {

    @NotEmpty(message = "Train number is required !!")
    @Size(min =3 ,max=20,message = "Invalid Length of Train Number")
    @Pattern(regexp="^\\d+$",message="Train no can only contain numbers")
    private String trainNo ;
    @Pattern(regexp="^[A-Za-z\\s-]+$", message="Only alphabets spaces and hyphens are allowed ")
    private String name;
    @ValidCoach
    private int coaches;

    public Train() {
    }

    public Train(String trainNo, String name, int coaches) {
        this.trainNo = trainNo;
        this.name = name;
        this.coaches = coaches;
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

    public int getCoaches() {
        return coaches;
    }

    public void setCoaches(int coaches) {
        this.coaches = coaches;
    }
}
