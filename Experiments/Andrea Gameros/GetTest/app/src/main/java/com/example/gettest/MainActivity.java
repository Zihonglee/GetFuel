package com.example.gettest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
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


public class MainActivity extends AppCompatActivity
{
    private TextView restaurantInfo;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurantInfo = (TextView) findViewById(R.id.etHomePage);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent newIntent = new Intent(view.getContext(), HomePage.class);
                startActivity(newIntent);
            }
        });
    }

    private void restaurantGetRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://3568159c-cded-4b55-906d-558bb5599e6e.mock.pstmn.io/v1/home";

        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        printResult.setText(response.toString());
                        try {
                            for(int i = 0; i < response.length(); i++) {

                                JSONObject restaurants = response.getJSONObject(i);
                                String firstName = restaurants.getString("restName");
                                String price = restaurants.getString("price");
                                String rating = restaurants.getString("rating");


                                restaurantInfo.append(firstName + ", " + price + ", "+ rating + "\n\n"); ;
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