package com.example.weatherforecast.api;

import com.example.weatherforecast.model.current_weather.CurrentWeather;
import com.example.weatherforecast.model.future_weather.FutureWeather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //Url trả về dữ liệu thời tiết hiện tại của thành phố
    //http://api.openweathermap.org/data/2.5/weather?q=hanoi&units=metric&appid=2bc77809541a0fc25f0e8f30f8d8dc18

    //Url trả về dữ liệu thời tiết của thành phố trong vòng 3 giờ và trong 5 ngày tiếp theo
    //http://api.openweathermap.org/data/2.5/forecast?q=hanoi&units=metric&appid=2bc77809541a0fc25f0e8f30f8d8dc18

    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    //Gọi api trả về dữ liệu thời tiết hiện tại khi biết tên thành phố
    @GET("data/2.5/weather")
    Call<CurrentWeather> convertCurrentWeather(@Query("q") String q,
                                        @Query("units") String units,
                                        @Query("appid") String appid);

    //Gọi api trả về dữ liệu thời tiết trong vòng 5 ngày và 3 giờ khi biết tên thành phố
    @GET("data/2.5/forecast")
    Call<FutureWeather> convertFutureWeather(@Query("q") String q,
                                        @Query("units") String units,
                                        @Query("appid") String appid);

}
