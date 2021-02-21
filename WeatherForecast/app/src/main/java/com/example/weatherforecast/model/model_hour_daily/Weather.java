package com.example.weatherforecast.model.model_hour_daily;

public class Weather {
    private int id;
    private String Clouds;
    private String description;
    private String icon;

    public Weather(int id, String clouds, String description, String icon) {
        this.id = id;
        Clouds = clouds;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClouds() {
        return Clouds;
    }

    public void setClouds(String clouds) {
        Clouds = clouds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
