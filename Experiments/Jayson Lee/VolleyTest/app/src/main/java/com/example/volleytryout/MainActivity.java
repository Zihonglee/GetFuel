package com.example.volleytryout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        restaurantGetRequest();
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
                                    String imageUrl = restaurants.getString("imageUrl");
                                    String restaurantName = restaurants.getString("name");
                                    String price = restaurants.getString("price");

                                    mExampleList.add(new ExampleItem(imageUrl, restaurantName, price));

                                }

                                mExampleAdapter = new MyAdapter(MainActivity.this, mExampleList);
                                recyclerView.setAdapter(mExampleAdapter);

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