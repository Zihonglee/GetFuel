package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView Price, Cuisine, Location, Rating, Name;
    private Button button;



    private String url = "https://3568159c-cded-4b55-906d-558bb5599e6e.mock.pstmn.io/v1/home";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.buttonResult);
        Price = (TextView) findViewById(R.id.priceText);



//        Cuisine = (TextView) findViewById(R.id.cuisineText);
//        Location = (TextView) findViewById(R.id.locationText);

        Rating = (TextView) findViewById(R.id.ratingText);
        Name = (TextView) findViewById(R.id.nameText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonObjectRequestResponse();
            }
        });
    }

    private void jsonObjectRequestResponse(){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{

//                            Cuisine.setText(response.getString("Cuisine"));
//                            Location.setText(response.getString("Location"));
                            Name.setText(response.getString("restName"));
                            Price.setText(response.getString("price"));
                            Rating.setText(response.getString("rating"));

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Something went wrong", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
            }
        });
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);

    }




}