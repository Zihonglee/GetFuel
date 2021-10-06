package com.example.jsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class addingRestaurantPage extends AppCompatActivity {
    private EditText restaurantInput, ratingInput, priceInput;
    private Button saveRestaurantBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_restaurant_page);

        restaurantInput = findViewById(R.id.inputRestaurant);
        priceInput = findViewById(R.id.inputPrice);
        ratingInput = findViewById(R.id.inputRating);
        saveRestaurantBtn = findViewById(R.id.saveRestaurant);

        saveRestaurantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               postRestaurant();
            }
        });

    }

    private void postRestaurant(){

        final String restaurantName, price, rating;
        RequestQueue restQueue = Volley.newRequestQueue(this);
        String restUrl = "http://coms-309-059.cs.iastate.edu:8080/restaurant";

        restaurantName= restaurantInput.getText().toString();
        price= priceInput.getText().toString();
        rating= ratingInput.getText().toString();


        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();

        try{
            object.put("name",restaurantName);
            object.put("price",price);
            object.put("rating", rating);
        }catch (JSONException e){
            e.printStackTrace();
        }
        array.put(object);
        JsonArrayRequest jsonRequest = new JsonArrayRequest( Request.Method.POST, restUrl, array,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        Toast.makeText(addingRestaurantPage.this, "Restaurant successfully added to API", Toast.LENGTH_SHORT).show();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(addingRestaurantPage.this, "Restaurant was not added to API", Toast.LENGTH_SHORT).show();


            }
        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError{
//                // below line we are creating a map for
//                // storing our values in key and value pair.
//                Map<String, String> params = new HashMap<String, String>();
//
//                // on below line we are passing our key
//                // and value pair to our parameters.
//                params.put("name", restaurantName);
//                params.put("price", price);
//                params.put("rating", rating);
//
//                return params;
//            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                String responseString;
                JSONArray array = new JSONArray();
                if (response != null) {

                    try {
                        responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        JSONObject obj = new JSONObject(responseString);
                        (array).put(obj);
                    } catch (Exception ex) {
                    }
                }
                //return array;
                return Response.success(array, HttpHeaderParser.parseCacheHeaders(response));
            }

        };


        restQueue.add(jsonRequest);
    }

    }




