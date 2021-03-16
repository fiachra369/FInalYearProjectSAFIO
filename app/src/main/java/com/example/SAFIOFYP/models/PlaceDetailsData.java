package com.example.SAFIOFYP.models;

public class PlaceDetailsData {
    String place_id;
    String address;

    public PlaceDetailsData(String place_id, String address) {
        this.place_id = place_id;
        this.address = address;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
