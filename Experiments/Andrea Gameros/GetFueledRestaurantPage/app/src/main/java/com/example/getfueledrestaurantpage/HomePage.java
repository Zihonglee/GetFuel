package com.example.getfueledrestaurantpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity
{
    private ImageButton RestaurantImage;
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        RestaurantImage = (ImageButton)findViewById(R.id.btnRestImg);


        RestaurantImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent newIntent = new Intent(view.getContext(), RestaurantPage.class);
                startActivity(newIntent);
            }
        });




        dl = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int id = item.getItemId();

                if(id == R.id.home)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(HomePage.this, "HomePage", Toast.LENGTH_SHORT).show();
                }

                if(id == R.id.search)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(HomePage.this, "Search", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePage.this, SearchPage.class);
                    startActivity(intent);
                }

                if(id == R.id.map)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(HomePage.this, "Map", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePage.this, MapPage.class);
                    startActivity(intent);
                }

                if(id == R.id.foodpicker)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(HomePage.this, "FoodPicker", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePage.this, FoodPickerPage.class);
                    startActivity(intent);
                }

                if(id == R.id.logout)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(HomePage.this, "Logout", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePage.this, LoginPage.class);
                    startActivity(intent);
                }


                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}