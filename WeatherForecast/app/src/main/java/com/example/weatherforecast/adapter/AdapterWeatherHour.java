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
import com.example.weatherforecast.model.model_hour_daily.WeatherHour;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterWeatherHour extends RecyclerView.Adapter<AdapterWeatherHour.WeatherHourViewHolder> {

    private Context context;
    private ArrayList<WeatherHour> weatherHourList;

    /**
     * Hàm tạo
     * @param context
     * @param weatherHourList
     */
    public AdapterWeatherHour(Context context, ArrayList<WeatherHour> weatherHourList) {
        this.context = context;
        this.weatherHourList = weatherHourList;
    }

    /**
     * Trả về 1 danh sách thời tiết theo giờ
     * @return
     */
    public ArrayList<WeatherHour> getWeatherHourList() {
        return weatherHourList;
    }

    public void setWeatherHourList(ArrayList<WeatherHour> weatherHourList) {
        this.weatherHourList = weatherHourList;
    }

    /**
     * Tạo layout hiện thị trên màn hình
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public WeatherHourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_layout, parent, false);
        return new WeatherHourViewHolder(view);
    }

    /**
     *  Xét dữ liệu hiển thị
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull WeatherHourViewHolder holder, int position) {
        WeatherHour weatherHour = weatherHourList.get(position);
        if (weatherHour != null) {
            holder.tv_hour.setText(weatherHour.getTime());
            holder.tv_status_hour.setText(weatherHour.getStatus());
            holder.tv_minTemp_hour.setText(String.valueOf(weatherHour.getMinTemp()) + "°C");
            holder.tv_maxTemp_hour.setText(String.valueOf(weatherHour.getMaxTemp()) + "°C");
            Picasso.get().load("http://openweathermap.org/img/wn/" + weatherHour.getIcon() + ".png").into(holder.img_icon_hour);
        }
        return;
    }

    /**
     * Số lượng item trong danh sách
     * @return
     */
    @Override
    public int getItemCount() {
        if (weatherHourList != null) {
            return weatherHourList.size();
        }
        return 0;
    }

    /**
     * Tạo WeatherHourViewHolder kế thừa từ ViewHolder trong RecyclerView
     */
    public class WeatherHourViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_hour, tv_status_hour, tv_minTemp_hour, tv_maxTemp_hour;
        private ImageView img_icon_hour;

        /**
         * Hàm tạo
         * @param itemView
         */
        public WeatherHourViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_hour = (TextView) itemView.findViewById(R.id.tv_hour);
            tv_status_hour = (TextView) itemView.findViewById(R.id.tv_status_hour);
            tv_minTemp_hour = (TextView) itemView.findViewById(R.id.tv_minTemp_hour);
            tv_maxTemp_hour = (TextView) itemView.findViewById(R.id.tv_maxTemp_hour);
            img_icon_hour = (ImageView) itemView.findViewById(R.id.img_icon_hour);
        }
    }
}
