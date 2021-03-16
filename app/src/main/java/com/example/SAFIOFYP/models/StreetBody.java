package com.example.SAFIOFYP.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StreetBody {
    @SerializedName("help")
    @Expose
    private String help;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private StreetResult streetResult;

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public StreetResult getResult() {
        return streetResult;
    }

    public void setResult(StreetResult streetResult) {
        this.streetResult = streetResult;
    }

    @Override
    public String toString() {
        return "StreetBody{" +
                "help='" + help + '\'' +
                ", success=" + success +
                ", streetResult=" + streetResult +
                '}';
    }
}
