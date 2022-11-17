package com.example.softwareengineeringproject_ian_huy.Object;

public class Car {


    public Car(String carID, String userEmail, String carState, String licensePlate, String carModel, String carColor) {
        this.carID = carID;
        this.userEmail = userEmail;
        this.carState = carState;
        this.licensePlate = licensePlate;
        this.carModel = carModel;
        this.carColor = carColor;
    }

    public Car() {

    }

    private String carID;
    private String userEmail;
    private String carState;
    private String licensePlate;
    private String carModel;



    private String carColor;

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    public String getuserEmail() {
        return userEmail;
    }

    public void setuserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }



}
