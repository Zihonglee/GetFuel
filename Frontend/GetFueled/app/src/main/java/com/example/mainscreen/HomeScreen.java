package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//@author-Andrea Gameros
//added implements...
public class HomeScreen extends AppCompatActivity //implements Adapter.OnNoteListener
{

    RecyclerView recyclerView;
    List<Restaurant> restaurants;
    private static String JSON_URL = "https://e29a5922-2c06-41b4-83a8-8141fc23a42b.mock.pstmn.io/restaurants";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen2);

        recyclerView = findViewById(R.id.recyclerView);
        restaurants = new ArrayList<>();
        
        extractRestaurants();

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new Adapter(this,restaurants);
        //recyclerView.setAdapter();
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

                        Toast.makeText(HomeScreen.this, "Restaurant", Toast.LENGTH_LONG).show();
                        Restaurant restaurant = new Restaurant();
                        restaurant.setName(restaurantObject.getString("name").toString());
                        restaurant.setCuisine(restaurantObject.getString("cuisine").toString());
                        restaurant.setRating(restaurantObject.getString("rating").toString());
                        restaurants.add(restaurant);
                    }
                    } catch (JSONException e) {
                    e.printStackTrace();
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(),restaurants); //adapter.mOnNoteListener); //added mOnNote..
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

    /**
    //added everything below
    @Override
    public void onNoteClick(int position)
    {
        //reference to selected restaurant
        restaurants.get(position);
        Intent intent = new Intent(this, RestaurantScreen.class);
        startActivity(intent);
    }


    /**
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        PullRestaurantList pullRestaurantList = new PullRestaurantList(HomeScreen.this);
        pullRestaurantList.execute();
    }
    **/
}