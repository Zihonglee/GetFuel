package com.example.jsontest;

import androidx.appcompat.app.AppCompatActivity;

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
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView printResult, postResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitButton = findViewById(R.id.submitButton);
        postResponse = findViewById(R.id.reviewComment);
        printResult = findViewById(R.id.result);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRequest();
            }
        });



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

                                printResult.append(firstName + "," + price + ", "+ rating + "\n\n") ;
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

    private void postRequest(){
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://reqres.in/api/users";

        JSONObject object = new JSONObject();
        try{
            object.put("comments","");
        }
        catch (JSONException e){
            e.printStackTrace();
        }


        JsonObjectRequest jsonRequestComment = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, "Review Submitted", Toast.LENGTH_LONG).show();
                requestQueue.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Failed to submit review", Toast.LENGTH_LONG).show();
                requestQueue.stop();
            }
        }){
//            @Override
//            protected Map<String, String> getParams(){
//                Map<String,String> params = new HashMap<String,String>();
//                params.put( "comments", postResponse.getText().toString());
//                return params;
//            }
        };

        requestQueue.add(jsonRequestComment);
    }
}