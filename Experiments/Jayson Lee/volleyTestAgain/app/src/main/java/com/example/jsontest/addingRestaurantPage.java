package com.example.jsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class addingRestaurantPage extends AppCompatActivity {
    private EditText restaurantInput, ratingInput, priceInput, imageUrlInput;
    private AutoCompleteTextView cuisineSelection;
    private Button saveRestaurantBtn;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> cuisineList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_restaurant_page);

        restaurantInput = findViewById(R.id.inputRestaurant);
        priceInput = findViewById(R.id.inputPrice);
        ratingInput = findViewById(R.id.inputRating);
        cuisineSelection = findViewById(R.id.autoCompleteTextView);
        imageUrlInput = findViewById(R.id.inputImageUrl);

        getCuisine();


        saveRestaurantBtn = findViewById(R.id.saveRestaurant);

        saveRestaurantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               postRestaurant();
            }
        });

    }

    private void getCuisine(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://coms-309-059.cs.iastate.edu:8080/cuisines";



        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        printResult.setText(response.toString());
                        try {
                            for(int i = 0; i < response.length(); i++) {

                                JSONObject cuisines = response.getJSONObject(i);
                                String cuisine = cuisines.getString("cuisineType");

                                cuisineList.add(cuisine);

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

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_item, cuisineList);
        cuisineSelection.setAdapter(arrayAdapter);


        queue.add(jsonRequest);

    }


    private void postRestaurant(){

        final String restaurantName, price, rating, imageUrl, cuisine;
        RequestQueue restQueue = Volley.newRequestQueue(this);
        String restUrl = "http://coms-309-059.cs.iastate.edu:8080/restaurant";

        restaurantName= restaurantInput.getText().toString();
        price= priceInput.getText().toString();
        rating= ratingInput.getText().toString();
        cuisine = cuisineSelection.getText().toString();
        imageUrl= imageUrlInput.getText().toString();



        abstract class MyJsonArrayRequest extends JsonRequest<JSONArray> {


            public MyJsonArrayRequest(int method, String url, JSONObject jsonRequest,
                                      Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
                super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(), listener,
                        errorListener);
            }
        }

// need to do dropdown for cuisines, since we are fixed with a certain cuisine already
// unless we are really adding a cuisine then we need to create a postCuisine method adding to cuisine url
        JSONObject object = new JSONObject();
        JSONObject cuisineJSON = new JSONObject();

        try{
            cuisineJSON.put("cuisineType", cuisine);
            object.put("name",restaurantName);
            object.put("price",price);
            object.put("rating", rating);
            object.put("cuisine", cuisineJSON.getJSONObject("cuisine"));
            object.put("url", imageUrl);


        }catch (JSONException e){
            e.printStackTrace();
        }

        MyJsonArrayRequest jsonRequest = new MyJsonArrayRequest( Request.Method.POST, restUrl, object,
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





