package com.example.softwareengineeringproject_ian_huy.Object;

public class Ticket {
    //Ticket(TicketID, userID LicensePlate, CarModel, State, Color, Parking Violation Type, Date)

    public Ticket(String ticketID) {
        this.ticketID = ticketID;
    }

    private String ticketID;

    public Ticket(String ticketID, String userID, String licensePlate, String carModel, String carState, String color, String parkingViolationType, String date) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.licensePlate = licensePlate;
        this.carModel = carModel;
        this.carState = carState;
        this.color = color;
        this.parkingViolationType = parkingViolationType;
        this.date = date;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getParkingViolationType() {
        return parkingViolationType;
    }

    public void setParkingViolationType(String parkingViolationType) {
        this.parkingViolationType = parkingViolationType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String userID;
    private String licensePlate;
    private String carModel;
    private String carState;
    private String color;
    private String parkingViolationType;
    private String date;

}
