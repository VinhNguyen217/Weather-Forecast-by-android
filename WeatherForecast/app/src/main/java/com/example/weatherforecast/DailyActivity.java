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

import com.example.weatherforecast.adapter.AdapterWeatherDaily;
import com.example.weatherforecast.api.ApiService;
import com.example.weatherforecast.model.future_weather.FutureWeather;
import com.example.weatherforecast.model.future_weather.GeneralWeather;
import com.example.weatherforecast.model.future_weather.List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyActivity extends AppCompatActivity {

    private String city;
    private TextView tv_city_daily;
    private RecyclerView rcv_weather_list_daily;
    private AdapterWeatherDaily adapterWeatherDaily;
    private ArrayList<GeneralWeather> generalWeatherList;

    public static final String UNITS = "metric";
    public static final String KEY_API = "2bc77809541a0fc25f0e8f30f8d8dc18";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Weather forecast for 5 days");

        Intent intent = getIntent();
        city = intent.getStringExtra("key_city");

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
                   tv_city_daily.setText(futureWeather.getCity().getName());

                   String Day = list.get(0).getDt_txt().split(" ")[0];
                   int count = 0;
                   for (int i=0;i<8;i++){
                       if(Day.equals(list.get(0).getDt_txt().split(" ")[0]))
                           count++;
                   }

                   for(int i=0;i<5;i++){
                       int x = count -1 +i*8;
                       String day = list.get(x).getDt_txt().split(" ")[0];
                       String status = list.get(x).getWeather().get(0).getDescription();
                       String icon = list.get(x).getWeather().get(0).getIcon();
                       float minTemp = list.get(x).getMain().getTemp_min();
                       float maxTemp = list.get(x).getMain().getTemp_max();
                       generalWeatherList.add(new GeneralWeather(day,status,icon,minTemp,maxTemp));
                   }
                   adapterWeatherDaily.setGeneralWeatherList(generalWeatherList);
                   adapterWeatherDaily.notifyDataSetChanged();
               }
           }

           @Override
           public void onFailure(Call<FutureWeather> call, Throwable t) {
               Toast.makeText(DailyActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
           }
       });
    }

    /**
     * Ánh xạ
     */
    private void getInit() {
        tv_city_daily = (TextView) findViewById(R.id.tv_city_daily);
        rcv_weather_list_daily = (RecyclerView) findViewById(R.id.rcv_weather_list_daily);
        generalWeatherList = new ArrayList<>();
        adapterWeatherDaily = new AdapterWeatherDaily(DailyActivity.this, generalWeatherList);
        rcv_weather_list_daily.setLayoutManager(new LinearLayoutManager(this));
        rcv_weather_list_daily.setAdapter(adapterWeatherDaily);
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