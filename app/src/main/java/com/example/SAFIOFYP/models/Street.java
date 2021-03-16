package com.example.SAFIOFYP.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Street {
    @SerializedName("_id")
    @Expose
    private Integer id;
    @SerializedName("street_name")
    @Expose
    private String streetName;
    @SerializedName("line_length")
    @Expose
    private Integer lineLength;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getLineLength() {
        return lineLength;
    }

    public void setLineLength(Integer lineLength) {
        this.lineLength = lineLength;
    }
}
