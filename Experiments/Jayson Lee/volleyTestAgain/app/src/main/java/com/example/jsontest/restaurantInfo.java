package com.example.jsontest;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class restaurantInfo extends AppCompatActivity {
    private TextView restaurantInfo;
    private Button reviewPageBtn, addRestaurantBtn, searchBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);

        restaurantInfo = findViewById(R.id.restInfo);
        reviewPageBtn = findViewById(R.id.goToReviewPage);
        addRestaurantBtn = findViewById(R.id.addRestBtn);
        searchBtn = findViewById(R.id.testSearch);

        restaurantGetRequest();

        reviewPageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(restaurantInfo.this, MainActivity.class);
                startActivity(intent);
            }
        });

        addRestaurantBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(restaurantInfo.this, addingRestaurantPage.class);
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(restaurantInfo.this, SearchPage.class);
                startActivity(intent);
            }
        });



    }


    private void restaurantGetRequest(){
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
                                String firstName = restaurants.getString("name");
                                String price = restaurants.getString("price");
                                String rating = restaurants.getString("rating");


                                restaurantInfo.append(i+1 + ". "+ firstName + ", " + price + ", "+ rating + "\n\n"); ;
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
}