package com.larryjune.dealership.model;

public class Image {
    private int imageID;
    private int vehicleID;
    private String imagePath;

    public Image(int imageID, int vehicleID, String imagePath) {
        this.imageID = imageID;
        this.vehicleID = vehicleID;
        this.imagePath = imagePath;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
