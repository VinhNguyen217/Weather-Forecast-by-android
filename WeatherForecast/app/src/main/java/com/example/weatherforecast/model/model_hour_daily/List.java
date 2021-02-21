package com.example.weatherforecast.model.model_hour_daily;

import com.example.weatherforecast.model.model_current.Clouds;
import com.example.weatherforecast.model.model_current.Wind;

import java.util.ArrayList;

public class List {
    private long dt;
    private Main main;
    private ArrayList<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private long visibility;
    private int pop;
    private Sys sys;
    private String dt_txt;

    public List(long dt, Main main, ArrayList<Weather> weather, Clouds clouds, Wind wind, long visibility, int pop, Sys sys, String dt_txt) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.clouds = clouds;
        this.wind = wind;
        this.visibility = visibility;
        this.pop = pop;
        this.sys = sys;
        this.dt_txt = dt_txt;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
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

    public long getVisibility() {
        return visibility;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
