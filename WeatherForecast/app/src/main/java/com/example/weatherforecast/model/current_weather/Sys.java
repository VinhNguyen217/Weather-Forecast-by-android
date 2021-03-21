package com.example.weatherforecast.model.current_weather;

public class Sys {
    private String country;

    public Sys(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
