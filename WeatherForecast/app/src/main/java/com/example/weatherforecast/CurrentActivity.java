package com.example.weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherforecast.api.ApiService;
import com.example.weatherforecast.model.model_current.ThoiTiet;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentActivity extends AppCompatActivity {

    private TextView tv_city, tv_country, tv_status, tv_temp, tv_minTemp, tv_maxTemp, tv_humidity, tv_clouds, tv_wind, tv_updateDay;
    private ImageView img_status, img_hourly, img_daily;
    private String fullDay, city;

    public static final String KEY_CITY = "key_city";
    public static final String KEY_DATE = "key_date";
    public static final String UNITS = "metric";
    public static final String KEY_API = "2bc77809541a0fc25f0e8f30f8d8dc18";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getInit();
        setEvent();

    }

    /**
     * Ánh xạ
     */
    private void getInit() {
        tv_city = findViewById(R.id.tv_city);
        tv_country = findViewById(R.id.tv_country);
        tv_status = findViewById(R.id.tv_status);
        tv_temp = findViewById(R.id.tv_temp);
        tv_minTemp = findViewById(R.id.tv_minTemp);
        tv_maxTemp = findViewById(R.id.tv_maxTemp);
        tv_humidity = findViewById(R.id.tv_humidity);
        tv_clouds = findViewById(R.id.tv_clouds);
        tv_wind = findViewById(R.id.tv_wind);
        tv_updateDay = findViewById(R.id.tv_updateDay);
        img_status = findViewById(R.id.img_status);
        img_hourly = findViewById(R.id.img_hourly);
        img_daily = findViewById(R.id.img_daily);
    }

    /**
     * Xét sự kiện
     */
    private void setEvent() {
        Intent intent = getIntent();
        city = intent.getStringExtra(KEY_CITY);
        callApi();
        img_hourly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(tv_city.getText().toString().trim())){
                    Intent intent1 = new Intent(CurrentActivity.this, HourActivity.class);
                    String date = fullDay.split(" ")[0] + " " + fullDay.split(" ")[1];
                    intent1.putExtra(KEY_CITY, city);
                    intent1.putExtra(KEY_DATE, date);
                    startActivity(intent1);
                    overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
                }else {
                    Toast.makeText(CurrentActivity.this, R.string.Authentic, Toast.LENGTH_SHORT).show();
                }

            }
        });
        img_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(tv_city.getText().toString().trim())){
                    Intent intent2 = new Intent(CurrentActivity.this, DailyActivity.class);
                    intent2.putExtra(KEY_CITY, city);
                    startActivity(intent2);
                    overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
                }else {
                    Toast.makeText(CurrentActivity.this, R.string.Authentic, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Gọi Api và xử lý dữ liệu trả về
     */
    public void callApi() {
        ApiService.apiService.convertWeather(city, UNITS, KEY_API).enqueue(new Callback<ThoiTiet>() {
            @Override
            public void onResponse(Call<ThoiTiet> call, Response<ThoiTiet> response) {
                ThoiTiet thoiTiet = response.body();
                if (thoiTiet != null) {
                    //city
                    tv_city.setText(thoiTiet.getName());
                    city = thoiTiet.getName();
                    //country
                    tv_country.setText(thoiTiet.getSys().getCountry());

                    //status
                    tv_status.setText(thoiTiet.getWeather().get(0).getMain());

                    //temp
                    String temp = String.valueOf(thoiTiet.getMain().getTemp());
                    tv_temp.setText(temp + "°C");

                    //min temp
                    String minTemp = String.valueOf(thoiTiet.getMain().getTemp_min());
                    tv_minTemp.setText(minTemp + "°C");

                    //max temp
                    String maxTemp = String.valueOf(thoiTiet.getMain().getTemp_max());
                    tv_maxTemp.setText(maxTemp + "°C");


                    //Độ ẩm
                    String humidity = String.valueOf(thoiTiet.getMain().getHumidity());
                    tv_humidity.setText(humidity + "%");

                    //Lượng mây
                    String clouds = String.valueOf(thoiTiet.getClouds().getAll());
                    tv_clouds.setText(clouds);

                    //tốc độ gió
                    String speed = String.valueOf(thoiTiet.getWind().getSpeed());
                    tv_wind.setText(speed + "m/s");

                    //Thời gian
                    tv_updateDay.setText(convertDay(thoiTiet.getDt()));

                    String icon = thoiTiet.getWeather().get(0).getIcon();
                    Picasso.get().load("http://openweathermap.org/img/wn/" + icon + ".png").into(img_status);
                }
            }

            @Override
            public void onFailure(Call<ThoiTiet> call, Throwable t) {
                Toast.makeText(CurrentActivity.this, R.string.Error, Toast.LENGTH_SHORT).show();
            }
        });
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
     * Trả về định dạng Thứ ngày-tháng-năm giờ-phút-giây
     * @param l
     * @return
     */
    public String convertDay(long l){
        Date date = new Date(l * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd-MM-yyyy HH:mm:ss");
        fullDay = simpleDateFormat.format(date);
        return fullDay;
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