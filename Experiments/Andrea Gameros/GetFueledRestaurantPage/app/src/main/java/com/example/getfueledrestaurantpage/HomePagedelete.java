package com.example.getfueledrestaurantpage;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomePagedelete extends AppCompatActivity
{
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage2);
        /**
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

                if(id == R.id.map)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(HomePage.this, "Map", Toast.LENGTH_SHORT).show();
                }

                if(id == R.id.foodpicker)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(HomePage.this, "FoodPicker", Toast.LENGTH_SHORT).show();
                }

                if(id == R.id.logout)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(HomePage.this, "Logout", Toast.LENGTH_SHORT).show();
                }


                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
    **/
    }
}
