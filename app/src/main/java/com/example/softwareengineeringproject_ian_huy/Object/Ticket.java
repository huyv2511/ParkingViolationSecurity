package com.example.softwareengineeringproject_ian_huy.Object;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.PropertyName;

@IgnoreExtraProperties
public class Ticket implements Parcelable {
    //Ticket(TicketID, userID LicensePlate, CarModel, State, Color, Parking Violation Type, Date)

    public Ticket(String ticketID) {
        this.ticketID = ticketID;
    }



    public Ticket(){

    }

    public Ticket(String ticketID, String licensePlate, String carModel, String carState, String color, String parkingViolationType, String date,String imageUri) {
        this.ticketID = ticketID;

        this.licensePlate = licensePlate;
        this.carModel = carModel;
        this.carState = carState;
        this.color = color;
        this.violationType = parkingViolationType;
        this.date = date;
        this.imageUri = imageUri;
    }

    protected Ticket(Parcel in) {
        userID = in.readString();
        licensePlate = in.readString();
        carModel = in.readString();
        carState = in.readString();
        color = in.readString();
        violationType = in.readString();
        date = in.readString();
        imageUri = in.readString();
        ticketID = in.readString();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

    @PropertyName("ticketID")
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
    @PropertyName("licensePlate")
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    @PropertyName("carModel")
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    @PropertyName("carState")
    public String getcarState() {
        return carState;
    }

    public void setcarState(String carState) {
        this.carState = carState;
    }
    @PropertyName("carColor")
    public String getcarColor() {
        return color;
    }

    public void setcarColor(String color) {
        this.color = color;
    }
    @PropertyName("violationType")
    public String getviolationType() {
        return violationType;
    }

    public void setviolationType(String violationType) {
        this.violationType = violationType;
    }
    @PropertyName("date")
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
    private String violationType;
    private String date;
    private String imageUri;
    private String ticketID;
    @PropertyName("imageUri")
    public String getImageUri() {
        return imageUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.imageUri = imageUri;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ticketID);
        parcel.writeString(licensePlate);
        parcel.writeString(carModel);
        parcel.writeString(carState);
        parcel.writeString(date);
        parcel.writeString(violationType);
        parcel.writeString(imageUri);
        parcel.writeString(userID);
    }
}
