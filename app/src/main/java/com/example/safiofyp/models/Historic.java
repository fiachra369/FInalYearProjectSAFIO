package com.example.safiofyp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historic {
    @SerializedName("available_bike_stands")
    @Expose
    private Integer availableBikeStands;
    @SerializedName("available_bikes")
    @Expose
    private Integer availableBikes;
    @SerializedName("bike_stands")
    @Expose
    private Integer bikeStands;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("time")
    @Expose
    private String time;

    public Integer getAvailableBikeStands() {
        return availableBikeStands;
    }

    public void setAvailableBikeStands(Integer availableBikeStands) {
        this.availableBikeStands = availableBikeStands;
    }

    public Integer getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(Integer availableBikes) {
        this.availableBikes = availableBikes;
    }

    public Integer getBikeStands() {
        return bikeStands;
    }

    public void setBikeStands(Integer bikeStands) {
        this.bikeStands = bikeStands;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
