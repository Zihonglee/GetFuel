package com.example.mainscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
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
import java.util.List;

//@author-Andrea Gameros
public class HomeScreen extends AppCompatActivity
{

    RecyclerView recyclerView;
    List<Restaurant> restaurants;
    //private static String JSON_URL = "https://e29a5922-2c06-41b4-83a8-8141fc23a42b.mock.pstmn.io/restaurants";
    //private static String JSON_URL = "https://d75f244e-33fd-4efa-bc17-a3c5ae9ffdc5.mock.pstmn.io/restaurants";
    private static String JSON_URL = "http://coms-309-059.cs.iastate.edu:8080/restaurant";
    Adapter adapter;

    private ImageButton RestaurantImage;
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen2);

        recyclerView = findViewById(R.id.recyclerView2);
        restaurants = new ArrayList<>();
        
        extractRestaurants();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,restaurants);
        recyclerView.setAdapter(adapter);


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
                    Toast.makeText(HomeScreen.this, "Home", Toast.LENGTH_SHORT).show();
                }

                if(id == R.id.search)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(HomeScreen.this, "Search", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeScreen.this, SearchScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.map)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(HomeScreen.this, "Map", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeScreen.this, MapScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.foodpicker)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(HomeScreen.this, "FoodPicker", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeScreen.this, FoodPicker.class);
                    startActivity(intent);
                }

                if(id == R.id.addRestaurant)
                {
                    Toast.makeText(HomeScreen.this, "Add Restaurant", Toast.LENGTH_SHORT).show();
                }

                if(id == R.id.logout)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(HomeScreen.this, "Logout", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeScreen.this, LoginScreen.class);
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


    private void extractRestaurants()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                try
                {
                    for(int i = 0; i < response.length(); i++)
                    {
                        JSONObject restaurantObject = response.getJSONObject(i);

                        Restaurant restaurant = new Restaurant();
                        restaurant.setName(restaurantObject.getString("name").toString());
                        //restaurant.setCuisine(restaurantObject.getString("cuisine").toString());
                        //restaurant.setRating(restaurantObject.getString("rating").toString());
                        restaurants.add(restaurant);
                    }
                    } catch (JSONException e) {
                    e.printStackTrace();
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(),restaurants);
                recyclerView.setAdapter(adapter);
            }
            },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeScreen.this, "Error", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        queue.add(jsonArrayRequest);
    }
}