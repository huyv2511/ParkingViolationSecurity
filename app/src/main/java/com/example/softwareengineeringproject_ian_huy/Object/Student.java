package com.example.softwareengineeringproject_ian_huy.Object;

public class Student {
    public Student(String userId, String userName, String userPassword, String userEmail, String phoneNumber, String fullName) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.PhoneNumber = phoneNumber;
        this.fullName = fullName;
        this.type = "Student";
    }

    public Student() {
    }

    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String PhoneNumber;
    private String fullName;
    private String type;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getuserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
