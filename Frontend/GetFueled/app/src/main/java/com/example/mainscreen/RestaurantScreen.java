package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

//@author-Andrea Gameros
public class RestaurantScreen extends AppCompatActivity
{
    public TextView Name;
    public TextView Price;
    public TextView Cuisine;
    public TextView Rating;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_screen);

        Name = (TextView)findViewById(R.id.etName2);
        Price = (TextView)findViewById(R.id.etPrice2);
        Cuisine = (TextView)findViewById(R.id.etCuisine2);
        Rating = (TextView)findViewById(R.id.etRating2);

        String name = getRestaurant();
        getInfo(name);

        //String n = name.getText().toString();
        //getInfo(n);
    }

    //how to pull info from homepage
    //put into get info which is called when made
    public String getRestaurant()
    {
        String name = Adapter.Name;
        return name;
    }

    private void getInfo(String s)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        //String url = "https://d75f244e-33fd-4efa-bc17-a3c5ae9ffdc5.mock.pstmn.io/restaurants";
        //String url = "https://2ae09dfa-c0e2-4a04-a9aa-66e4ada57766.mock.pstmn.io";
        String url = "http://coms-309-059.cs.iastate.edu:8080/restaurant";


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
                                //String priceJ = restaurants.getString("price");
                                //String cuisineJ = restaurants.getString("cuisine");
                                //String ratingJ = restaurants.getString("rating");


                                if(s.equals(nameJ))
                                {
                                    String priceJ = restaurants.getString("price");
                                    //String cuisineJ = restaurants.getString("cuisine");
                                    String ratingJ = restaurants.getString("rating");

                                    Name.append(nameJ);
                                    Price.append(priceJ);
                                    //Cuisine.append(cuisineJ);
                                    Rating.append(ratingJ);

                                    JSONObject cuisine = restaurants.getJSONObject("cuisine");
                                    String cuisineJ = cuisine.getString("cuisineType");
                                    Cuisine.append(cuisineJ);
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
                Toast.makeText(RestaurantScreen.this, "Error bitch", Toast.LENGTH_LONG).show();
                error.printStackTrace();
                queue.stop();
            }
        });

        queue.add(jsonRequest);
    }
}