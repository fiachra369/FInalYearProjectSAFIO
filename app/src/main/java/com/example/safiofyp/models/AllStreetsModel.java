package com.example.safiofyp.models;

public class AllStreetsModel {
    String streetName;
    int streetAverage;
    int streetThresh;
    int streetLength;
    int streetWidth;

    public AllStreetsModel(String streetName, int streetAverage, int streetThresh, int streetLength, int streetWidth) {
        this.streetName = streetName;
        this.streetAverage = streetAverage;
        this.streetThresh = streetThresh;
        this.streetLength = streetLength;
        this.streetWidth = streetWidth;
    }


    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetAverage() {
        return streetAverage;
    }

    public void setStreetAverage(int streetAverage) {
        this.streetAverage = streetAverage;
    }

    public int getStreetThresh() {
        return streetThresh;
    }

    public void setStreetThresh(int streetThresh) {
        this.streetThresh = streetThresh;
    }

    public int getStreetLength() {
        return streetLength;
    }

    public void setStreetLength(int streetLength) {
        this.streetLength = streetLength;
    }

    public int getStreetWidth() {
        return streetWidth;
    }

    public void setStreetWidth(int streetWidth) {
        this.streetWidth = streetWidth;
    }


}
