package com.example.getpostrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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

public class Homepage extends AppCompatActivity
{
    public TextView r1;
    public TextView r2;
    public static String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        r1 = (TextView)findViewById(R.id.tvR1);
        r2 = (TextView)findViewById(R.id.tvR2);

        r1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent newIntent = new Intent(view.getContext(), RestaurantPage.class);
                startActivity(newIntent);
            }
        });

        r2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent newIntent = new Intent(view.getContext(), RestaurantPage.class);
                startActivity(newIntent);
            }
        });
    }


    /**
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
                Toast.makeText(Homepage.this, "Error bitch", Toast.LENGTH_LONG).show();
                error.printStackTrace();
                queue.stop();
            }
        });

        queue.add(jsonRequest);
    }

    /**
    public String returnName(String s)
    {

    }
     **/
}