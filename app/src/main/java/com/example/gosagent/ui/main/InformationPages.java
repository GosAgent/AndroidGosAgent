package com.example.gosagent.ui.main;

// Look in DB, what colons from Db.

import java.util.ArrayList;

public class InformationPages {
    // Class of objects (information of lot). Take from DB.
    private int image;
    private String title;
    private String nameOfLot; /// check this from db
    private String description;
    private String link;
    private ArrayList<Double> coordinates;
    private ArrayList<Double> otherInformation;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameOfLot() {
        return nameOfLot;
    }

    public void setNameOfLot(String nameOfLot) {
        this.nameOfLot = nameOfLot;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<Double> getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(ArrayList<Double> otherInformation) {
        this.otherInformation = otherInformation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
