package com.example.weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.weatherforecast.R.string.Input;

public class MainActivity extends AppCompatActivity {

    private EditText edt_city;
    private ImageView img_search;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        getInit();

        setEvent();
    }

    /**
     * Ánh xạ
     */
    private void getInit() {
        edt_city = (EditText) findViewById(R.id.edt_city);
        img_search = (ImageView) findViewById(R.id.img_search);
    }

    /**
     * Bắt sự kiện
     */
    private void setEvent() {
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = edt_city.getText().toString().trim();
                if (TextUtils.isEmpty(city)) {
                    Toast.makeText(MainActivity.this, Input, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, CurrentActivity.class);
                    intent.putExtra("key_city", city);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter_from_left,R.anim.exit_out_left);
                }
            }
        });
    }
}