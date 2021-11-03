package com.example.mainscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class SearchScreen extends AppCompatActivity
{

    private EditText Search;
    private Button SearchButton;

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
        Search = (EditText)findViewById(R.id.etSearch);
        SearchButton = (Button)findViewById(R.id.btnSearch);

        String SearchInfo = Search.getText().toString();

        //when click search, search options appear
        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

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
                    Toast.makeText(SearchScreen.this, "Home", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SearchScreen.this, HomeScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.search)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(SearchScreen.this, "Search", Toast.LENGTH_SHORT).show();
                }

                if(id == R.id.map)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(SearchScreen.this, "Map", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SearchScreen.this, MapScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.foodpicker)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(SearchScreen.this, "FoodPicker", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(HomeScreen.this, FoodPickerPage.class);
                    //startActivity(intent);
                }

                if(id == R.id.logout)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(SearchScreen.this, "Logout", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SearchScreen.this, LoginScreen.class);
                    startActivity(intent);
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
}