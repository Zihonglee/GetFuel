package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//@author-Andrea Gameros
public class HomeScreen extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        PullRestaurantList pullRestaurantList = new PullRestaurantList(HomeScreen.this);
        pullRestaurantList.execute();
    }
}