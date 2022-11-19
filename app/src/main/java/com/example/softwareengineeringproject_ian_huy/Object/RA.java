package com.example.softwareengineeringproject_ian_huy.Object;

public class RA {
    private String name;

    public RA(String name, String phoneNumber, String email, String buildingPos) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.buildingPos = buildingPos;
    }

    public RA() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBuildingPos() {
        return buildingPos;
    }

    public void setBuildingPos(String buildingPos) {
        this.buildingPos = buildingPos;
    }

    private String phoneNumber;
    private String email;
    private String buildingPos;
}
