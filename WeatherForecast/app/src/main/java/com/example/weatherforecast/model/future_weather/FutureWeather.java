package com.example.weatherforecast.model.future_weather;

import java.util.ArrayList;

public class FutureWeather {
    private ArrayList<List>  list;
    private City city;

    public FutureWeather(ArrayList<List> list, City city) {
        this.list = list;
        this.city = city;
    }

    public ArrayList<List> getList() {
        return list;
    }

    public void setList(ArrayList<List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
