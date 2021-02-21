package com.example.weatherforecast.model.model_hour_daily;

public class WeatherHour {
    private String time;
    private String status;
    private String icon;
    private float minTemp;
    private float maxTemp;

    public WeatherHour(String time, String status, String icon, float minTemp, float maxTemp) {
        this.time = time;
        this.status = status;
        this.icon = icon;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }
}
