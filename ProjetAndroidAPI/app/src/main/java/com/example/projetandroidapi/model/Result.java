package com.example.projetandroidapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("marque")
    @Expose
    private Object marque;
    @SerializedName("opening_hours")
    @Expose
    private Object openingHours;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("meta_code_dep")
    @Expose
    private String metaCodeDep;
    @SerializedName("meta_name_com")
    @Expose
    private String metaNameCom;
    @SerializedName("meta_code_com")
    @Expose
    private String metaCodeCom;
    @SerializedName("meta_geo_point")
    @Expose
    private MetaGeoPoint metaGeoPoint;
    @SerializedName("additionalProperties")
    @Expose
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getMarque() {
        return marque;
    }

    public void setMarque(Object marque) {
        this.marque = marque;
    }

    public Object getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(Object openingHours) {
        this.openingHours = openingHours;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String  getMetaNameCom() {
        return metaNameCom;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getMetaCodeDep() {
        return metaCodeDep;
    }

    public String getMetaCodeCom() {
        return metaCodeCom;
    }

    public void setMetaCodeDep(String metaCodeDep) {
        this.metaCodeDep = metaCodeDep;
    }

    public MetaGeoPoint getMetaGeoPoint() {
        return metaGeoPoint;
    }

    public void setMetaGeoPoint(MetaGeoPoint metaGeoPoint) {
        this.metaGeoPoint = metaGeoPoint;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
