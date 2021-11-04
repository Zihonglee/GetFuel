package com.example.jsontest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class restaurantDatabase extends AppCompatActivity {

    ListView listView;
    ArrayList<Restaurant> restaurantList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_database);

        listView = findViewById(R.id.restaurantDataList);
        getRestaurants();




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




}