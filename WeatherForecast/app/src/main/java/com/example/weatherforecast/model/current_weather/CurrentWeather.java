package com.example.weatherforecast.model.current_weather;


import java.util.ArrayList;

public class CurrentWeather {
    private String name;
    private Sys sys;
    private ArrayList<Weather> weather;
    private Main main;
    private Clouds clouds;
    private Wind wind;
    private long dt;

    public CurrentWeather(String name, Sys sys, ArrayList<Weather> weather, Main main, Clouds clouds, Wind wind, long dt) {
        this.name = name;
        this.sys = sys;
        this.weather = weather;
        this.main = main;
        this.clouds = clouds;
        this.wind = wind;
        this.dt = dt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }
}
