package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class FoodPicker extends AppCompatActivity {

    //@Author Jayson Lee
    private LuckyWheel lw;
    ArrayList<WheelItem> wheelItems;
    ArrayList<String> randomRestaurants = new ArrayList<String>();

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