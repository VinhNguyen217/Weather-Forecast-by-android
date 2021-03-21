package com.example.weatherforecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforecast.R;
import com.example.weatherforecast.model.future_weather.GeneralWeather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterWeatherDaily extends RecyclerView.Adapter<AdapterWeatherDaily.WeatherDailyViewHolder>{

    private Context context;
    private ArrayList<GeneralWeather> generalWeatherList;

    public AdapterWeatherDaily(Context context, ArrayList<GeneralWeather> generalWeatherList) {
        this.context = context;
        this.generalWeatherList = generalWeatherList;
    }


    public ArrayList<GeneralWeather> getGeneralWeatherList() {
        return generalWeatherList;
    }

    public void setGeneralWeatherList(ArrayList<GeneralWeather> generalWeatherList) {
        this.generalWeatherList = generalWeatherList;
    }

    @NonNull
    @Override
    public WeatherDailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_layout,parent,false);
        return new WeatherDailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherDailyViewHolder holder, int position) {
        GeneralWeather generalWeather = generalWeatherList.get(position);
        if(generalWeather != null){
            holder.tv_hour.setText(generalWeather.getTime());
            holder.tv_status_hour.setText(generalWeather.getStatus());
            holder.tv_minTemp_hour.setText(String.valueOf(generalWeather.getMinTemp()) + "°C");
            holder.tv_maxTemp_hour.setText(String.valueOf(generalWeather.getMaxTemp()) + "°C");
            Picasso.get().load("http://openweathermap.org/img/wn/" + generalWeather.getIcon() + ".png").into(holder.img_icon_hour);
        }
        return;
    }

    @Override
    public int getItemCount() {
        if(generalWeatherList != null){
            return generalWeatherList.size();
        }
        return 0;
    }

    public class WeatherDailyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_hour, tv_status_hour, tv_minTemp_hour, tv_maxTemp_hour;
        private ImageView img_icon_hour;

        public WeatherDailyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_hour = (TextView) itemView.findViewById(R.id.tv_hour);
            tv_status_hour = (TextView) itemView.findViewById(R.id.tv_status_hour);
            tv_minTemp_hour = (TextView) itemView.findViewById(R.id.tv_minTemp_hour);
            tv_maxTemp_hour = (TextView) itemView.findViewById(R.id.tv_maxTemp_hour);
            img_icon_hour = (ImageView) itemView.findViewById(R.id.img_icon_hour);
        }
    }
}
