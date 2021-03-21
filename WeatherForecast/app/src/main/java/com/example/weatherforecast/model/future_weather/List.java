package com.example.weatherforecast.model.future_weather;

import java.util.ArrayList;

public class List {
    private Main main;
    private ArrayList<Weather> weather;
    private String dt_txt;

    public List(Main main, ArrayList<Weather> weather, String dt_txt) {
        this.main = main;
        this.weather = weather;
        this.dt_txt = dt_txt;
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

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}

