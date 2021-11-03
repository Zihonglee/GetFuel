package com.example.mainscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class FoodPicker extends AppCompatActivity {


    private LuckyWheel lw;
    ArrayList<WheelItem> wheelItems;
    ArrayList<String> randomRestaurants = new ArrayList<String>();

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_picker);

        generateWheelItems();
        wheelList();

        lw = findViewById(R.id.lwv);
        lw.addWheelItems(wheelItems);
        lw.setTarget(1);
        Random rand = new Random();

        lw.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                Toast.makeText(FoodPicker.this, "Your restaurant", Toast.LENGTH_SHORT).show();
            }
        });

        Button spinBtn = findViewById(R.id.spinButton);
        spinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lw.rotateWheelTo(rand.nextInt(5));
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
                    Toast.makeText(FoodPicker.this, "Home", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FoodPicker.this, HomeScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.search)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(FoodPicker.this, "Search", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FoodPicker.this, SearchScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.map)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(FoodPicker.this, "Map", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FoodPicker.this, MapScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.foodpicker)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(FoodPicker.this, "FoodPicker", Toast.LENGTH_SHORT).show();
                }

                if(id == R.id.logout)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(FoodPicker.this, "Logout", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FoodPicker.this, LoginScreen.class);
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

    private void generateWheelItems(){



        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://coms-309-059.cs.iastate.edu:8080/restaurant";



        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i = 0; i < response.length(); i++) {

                                JSONObject restaurants = response.getJSONObject(i);

                                String restaurantName = restaurants.getString("name");
                                randomRestaurants.add(restaurantName);



                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        queue.stop();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                queue.stop();
            }
        });


        queue.add(jsonRequest);
    }

    private void wheelList(){
        wheelItems = new ArrayList<>();
        Random random = new Random();

        wheelItems.add(new WheelItem(Color.parseColor("#fc6c6c"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name),
                "Thai Kitchen"));
        wheelItems.add(new WheelItem(Color.parseColor("red"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name),
                "Taste Place"));
        wheelItems.add(new WheelItem(Color.parseColor("black"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name),
                "Ichiban"));
        wheelItems.add(new WheelItem(Color.parseColor("gray"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name),
                "Union Drive Marketplace"));
        wheelItems.add(new WheelItem(Color.parseColor("green"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name),
                "Friley Windows"));
        wheelItems.add(new WheelItem(Color.parseColor("blue"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name),
                "Freddy's"));
    }

}