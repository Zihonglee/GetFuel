package com.example.getpostrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity
{
    private TextView r1;
    private TextView r2;
    private TextView Name;
    private TextView Price;
    private TextView Cuisine;
    private TextView Rating;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r1 = (TextView)findViewById(R.id.etR1);
        r2 = (TextView)findViewById(R.id.etR2);
        Name = (TextView)findViewById(R.id.etName2);
        Price = (TextView)findViewById(R.id.etPrice2);
        Cuisine = (TextView)findViewById(R.id.etCuisine2);
        Rating = (TextView)findViewById(R.id.etRating2);

        r1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this, "Click 1", Toast.LENGTH_LONG).show();
                String r = r1.getText().toString();
                //append(r);
                getInfo(r);
            }
        });

        r2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(MainActivity.this, "Click 2", Toast.LENGTH_LONG).show();
                String r = r2.getText().toString();
                //append(r);
                getInfo(r);
            }
        });
    }

    private void getInfo(String s)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://e29a5922-2c06-41b4-83a8-8141fc23a42b.mock.pstmn.io/restaurants";

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
                                String priceJ = restaurants.getString("price");
                                String cuisineJ = restaurants.getString("cuisine");
                                String ratingJ = restaurants.getString("rating");


                                if(s.equals(nameJ))
                                {
                                    Name.append(nameJ);
                                    Price.append(priceJ);
                                    Cuisine.append(cuisineJ);
                                    Rating.append(ratingJ);
                                   //Toast.makeText(MainActivity.this,"Equal found",Toast.LENGTH_LONG).show();
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
                Toast.makeText(MainActivity.this, "Error bitch", Toast.LENGTH_LONG).show();
                error.printStackTrace();
                queue.stop();
            }
        });

        queue.add(jsonRequest);
    }



    /**
    private void append(String r)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://e29a5922-2c06-41b4-83a8-8141fc23a42b.mock.pstmn.io/restaurants";

        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        try
                        {
                            for(int i = 0; i < response.length(); i++ )
                            {
                                Toast.makeText(MainActivity.this, "Ay", Toast.LENGTH_LONG).show();
                                JSONObject restaurants = response.getJSONObject(i);
                                String nameJ = restaurants.getString("name");
                                String priceJ = restaurants.getString("price");
                                String cuisineJ = restaurants.getString("cuisine");
                                String ratingJ = restaurants.getString("rating");

                                if(r.equals(nameJ)) {
                                    Toast.makeText(MainActivity.this, "DOUBLE YAY", Toast.LENGTH_LONG).show();
                                    Name.append(nameJ);
                                    Price.append(priceJ);
                                    Cuisine.append(cuisineJ);
                                    Rating.append(ratingJ);
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
                Toast.makeText(MainActivity.this, "Error bitch", Toast.LENGTH_LONG).show();
                error.printStackTrace();
                queue.stop();
            }
        });

        queue.add(jsonRequest);
    }
    **/

    /**
    public void onClick(View view)
    {
       // String r = this.getText().toString();
        Toast.makeText(MainActivity.this, "Click 2", Toast.LENGTH_LONG).show();
    }
     **/
}