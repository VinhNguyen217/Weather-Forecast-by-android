package com.example.weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherforecast.adapter.AdapterWeatherHour;
import com.example.weatherforecast.api.ApiService;
import com.example.weatherforecast.model.future_weather.FutureWeather;
import com.example.weatherforecast.model.future_weather.GeneralWeather;
import com.example.weatherforecast.model.future_weather.List;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HourActivity extends AppCompatActivity {

    private String city, date;
    private TextView tv_city_hour, tv_date_hour;
    private RecyclerView rcv_weather_list_hour;
    private AdapterWeatherHour adapterWeatherHour;
    private ArrayList<GeneralWeather> generalWeatherList;

    public static final String KEY_CITY = "key_city";
    public static final String KEY_DATE = "key_date";
    public static final String UNITS = "metric";
    public static final String KEY_API = "2bc77809541a0fc25f0e8f30f8d8dc18";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Weather forecast for 3 hours");

        Intent intent = getIntent();
        city = intent.getStringExtra(KEY_CITY);
        date = intent.getStringExtra(KEY_DATE);

        getInit();

        callApi();
    }

    /**
     * Gọi Api và xử lý dữ liệu trả về
     */
    private void callApi() {
     ApiService.apiService.convertFutureWeather(city,UNITS,KEY_API).enqueue(new Callback<FutureWeather>() {
         @Override
         public void onResponse(Call<FutureWeather> call, Response<FutureWeather> response) {

             FutureWeather futureWeather = response.body();
             if(futureWeather != null){
                 ArrayList<List> list = futureWeather.getList();

                 //City
                 tv_city_hour.setText(futureWeather.getCity().getName());
                 //Date
                 tv_date_hour.setText(date);

                 String Day = list.get(0).getDt_txt().split(" ")[0];
                 for (int i=0;i<8;i++){
                     if(Day.equals(list.get(i).getDt_txt().split(" ")[0])){
                         String time = list.get(i).getDt_txt().split(" ")[1];
                         String status = list.get(i).getWeather().get(0).getDescription();
                         String icon = list.get(i).getWeather().get(0).getIcon();
                         float minTemp = list.get(i).getMain().getTemp_min();
                         float maxTemp = list.get(i).getMain().getTemp_max();
                         generalWeatherList.add(new GeneralWeather(time,status,icon,minTemp,maxTemp));
                     }
                 }
                 adapterWeatherHour.setGeneralWeatherList(generalWeatherList);
                 adapterWeatherHour.notifyDataSetChanged();
             }
         }

         @Override
         public void onFailure(Call<FutureWeather> call, Throwable t) {
             Toast.makeText(HourActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
         }
     });
    }

    /**
     * Ánh xạ
     */
    private void getInit() {
        tv_city_hour = (TextView) findViewById(R.id.tv_city_hour);
        tv_date_hour = (TextView) findViewById(R.id.tv_date_hour);
        rcv_weather_list_hour = (RecyclerView) findViewById(R.id.rcv_weather_list_hour);
        generalWeatherList = new ArrayList<>();
        adapterWeatherHour = new AdapterWeatherHour(HourActivity.this, generalWeatherList);
        rcv_weather_list_hour.setLayoutManager(new LinearLayoutManager(HourActivity.this));
        rcv_weather_list_hour.setAdapter(adapterWeatherHour);
    }

    /**
     * Xử lý nút quay về trên thanh actionBar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Xử lý nút back
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
    }
}