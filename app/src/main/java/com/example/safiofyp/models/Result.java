package com.example.safiofyp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

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
    private List<Record> records = null;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("sql")
    @Expose
    private String sql;

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

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
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

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
