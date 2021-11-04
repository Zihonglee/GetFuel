package com.example.mainscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
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

//@author-Andrea Gameros
public class RestaurantScreen extends AppCompatActivity
{
    public TextView Name;
    public TextView Price;
    public TextView Cuisine;
    public TextView Rating;

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_screen);

        Name = (TextView)findViewById(R.id.etName2);
        Price = (TextView)findViewById(R.id.etPrice2);
        Cuisine = (TextView)findViewById(R.id.etCuisine2);
        Rating = (TextView)findViewById(R.id.etRating2);

        String name = getRestaurant();
        getInfo(name);

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
                    Toast.makeText(RestaurantScreen.this, "Home", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RestaurantScreen.this, HomeScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.search)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(RestaurantScreen.this, "Search", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RestaurantScreen.this, SearchScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.map)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(RestaurantScreen.this, "Map", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RestaurantScreen.this, MapScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.foodpicker)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(RestaurantScreen.this, "FoodPicker", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RestaurantScreen.this, FoodPicker.class);
                    startActivity(intent);
                }

                if(id == R.id.addRestaurant)
                {
                    Toast.makeText(RestaurantScreen.this, "Add Restaurant", Toast.LENGTH_SHORT).show();
                }

                if(id == R.id.logout)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(RestaurantScreen.this, "Logout", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RestaurantScreen.this, LoginScreen.class);
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

    //how to pull info from homepage
    //put into get info which is called when made
    public String getRestaurant()
    {
        String name = Adapter.Name;
        return name;
    }

    private void getInfo(String s)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        //String url = "https://d75f244e-33fd-4efa-bc17-a3c5ae9ffdc5.mock.pstmn.io/restaurants";
        //String url = "https://2ae09dfa-c0e2-4a04-a9aa-66e4ada57766.mock.pstmn.io";
        String url = "http://coms-309-059.cs.iastate.edu:8080/restaurant";


        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        try {
                            for(int i = 0; i < response.length(); i++)
                            {
                                JSONObject restaurants = response.getJSONObject(i);
                                String nameJ = restaurants.getString("name");


                                if(s.equals(nameJ))
                                {
                                    String priceJ = restaurants.getString("price");
                                    String ratingJ = restaurants.getString("rating");

                                    Name.append(nameJ);
                                    Price.append(priceJ);
                                    Rating.append(ratingJ);

                                    //goes thru json object for cuisine to find the type
                                    JSONObject cuisine = restaurants.getJSONObject("cuisine");
                                    String cuisineJ = cuisine.getString("cuisineType");
                                    Cuisine.append(cuisineJ);

                                    break;
                                }
                            }
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        queue.stop();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(RestaurantScreen.this, "Error", Toast.LENGTH_LONG).show();
                error.printStackTrace();
                queue.stop();
            }
        });

        queue.add(jsonRequest);
    }
}