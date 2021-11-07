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
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class databaseRestaurant extends AppCompatActivity {

    ListView listView;
    ArrayList<Restaurant> restaurantList = new ArrayList<>();
    Button addRestaurantBtn;
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_restaurant);

        addRestaurantBtn = findViewById(R.id.addRestBtn);
        listView = findViewById(R.id.restaurantDataList);
        getRestaurants();

        addRestaurantBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(databaseRestaurant.this, addingRestaurantPage.class);
                startActivity(intent);
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
                    Toast.makeText(databaseRestaurant.this, "Home", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(databaseRestaurant.this, HomeScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.search)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(databaseRestaurant.this, "Search", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(databaseRestaurant.this, SearchScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.map)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(databaseRestaurant.this, "Map", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(databaseRestaurant.this, MapScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.foodpicker)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(databaseRestaurant.this, "FoodPicker", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(databaseRestaurant.this, FoodPicker.class);
                    startActivity(intent);
                }

                if(id == R.id.addRestaurant)
                {
                    Toast.makeText(databaseRestaurant.this, "Add Restaurant", Toast.LENGTH_SHORT).show();

                }

                if(id == R.id.logout)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(databaseRestaurant.this, "Logout", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(databaseRestaurant.this, LoginScreen.class);
                    startActivity(intent);
                }


                return true;
            }
        });
    }


    private void getRestaurants(){
        RequestQueue queue = Volley.newRequestQueue(this);



        String url = "http://coms-309-059.cs.iastate.edu:8080/restaurant";



        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        printResult.setText(response.toString());
                        try {
                            for(int i = 0; i < response.length(); i++) {

                                JSONObject restaurants = response.getJSONObject(i);
                                String id = restaurants.getString("id");
                                String restaurantName = restaurants.getString("name");

                                Restaurant restInfo = new Restaurant(id, restaurantName);
                                restaurantList.add(restInfo);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RestaurantListAdapter adapter = new RestaurantListAdapter(getApplicationContext(), R.layout.databaserow, restaurantList);
                        listView.setAdapter(adapter);

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



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}