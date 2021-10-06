package com.example.jsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText postResponse;
    private TextView listOfReviews;
    private Button  submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.submitButton);
        listOfReviews = findViewById(R.id.listOfReview);
        postResponse = findViewById(R.id.reviewComment);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRequest();

            }
        });

    }

    private void postRequest(){
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        final String comments;
        String reviewUrl = "http://coms-309-059.cs.iastate.edu:8080/review";

        comments = postResponse.getText().toString();

//        JSONObject object = new JSONObject();
//        try{
//            object.put("comments",postResponse.getText());
//        }
//        catch (JSONException e){
//            e.printStackTrace();
//        }

       abstract class MyJsonArrayRequest extends JsonRequest<JSONArray> {


            public MyJsonArrayRequest(int method, String url, JSONObject jsonRequest,
                                      Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
                super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(), listener,
                        errorListener);
            }
        }


        JSONObject object = new JSONObject();

        try{
            object.put("comments",comments);
        }catch (JSONException e){
            e.printStackTrace();
        }


        MyJsonArrayRequest jsonArrayRequestComment = new MyJsonArrayRequest (Request.Method.POST, reviewUrl, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
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

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        requestQueue.add(jsonArrayRequestComment);
    }

//    private void reviewsGetRequest(){
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        String url = "http://coms-309-059.cs.iastate.edu:8080/review";
//
//
//
//        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
////                        printResult.setText(response.toString());
//                        try {
//                            for(int i = 0; i < response.length(); i++) {
//
//                                JSONObject review = response.getJSONObject(i);
//                                String comments = review.getString("comments");
//
//
//
//                                listOfReviews.append(comments+ ", " + "\n\n"); ;
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        queue.stop();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                queue.stop();
//            }
//        });
//
//        queue.add(jsonRequest);
//    }
}