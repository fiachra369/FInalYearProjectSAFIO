package com.example.SAFIOFYP.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StreetResult {
    @SerializedName("include_total")
    @Expose
    private Boolean includeTotal;
    @SerializedName("resource_id")
    @Expose
    private String resourceId;
    @SerializedName("fields")
    @Expose
    private List<Field> fields = null;
    @SerializedName("records_format")
    @Expose
    private String recordsFormat;
    @SerializedName("records")
    @Expose
    private List<Street> streetsList = null;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("total")
    @Expose
    private Integer total;

    public Boolean getIncludeTotal() {
        return includeTotal;
    }

    public void setIncludeTotal(Boolean includeTotal) {
        this.includeTotal = includeTotal;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getRecordsFormat() {
        return recordsFormat;
    }

    public void setRecordsFormat(String recordsFormat) {
        this.recordsFormat = recordsFormat;
    }

    public List<Street> getStreets() {
        return streetsList;
    }

    public void setRecords(List<Street> streets) {
        this.streetsList = streetsList;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
