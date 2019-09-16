package com.henrique.fructose.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

import com.henrique.fructose.R;
import com.henrique.fructose.model.restaurant.RestaurantX;

public class RestaurantDetailsActivity extends AppCompatActivity {

    RestaurantX restaurantInfo;
    ImageView imagemzinea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        setupLayout();
        getData();
    }

    private void getData() {
        if(getIntent().hasExtra("restaurant")){
            restaurantInfo = (RestaurantX) getIntent().getSerializableExtra("restaurant");
        }
    }

    @SuppressLint("CheckResult")
    private void setupLayout() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action",
                Snackbar.LENGTH_LONG).setAction("Action", null).show());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {

        Glide.with(this).load(restaurantInfo.getPhotos()
                .get(0).getPhoto().getUrl()).centerCrop();

        }catch (Exception ignore){}

    }

}
