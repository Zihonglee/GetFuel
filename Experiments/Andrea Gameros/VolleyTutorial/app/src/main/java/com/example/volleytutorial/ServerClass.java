package com.example.volleytutorial;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import org.json.JSONObject;
import org.json.JSONArray;

import java.net.URL;

public class ServerClass
{
    final static String TAG = ServerClass.class.getName();

    //makes requests

    public void sendPostRequestsToServer(Context context, JSONObject jsonObject, String URL, final ServerResponseCallback serverResonseCallback)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //make request object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d(TAG, "onResponse: " + response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e(TAG, "onErrorResponse: ", error);
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    public void sendGETRequestsToServer(Context context, JSONObject jsonObject, String URL, final ServerResponseCallback serverResonseCallback)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null, new Response.Listener<JSONArray>()
                {
                     @Override
                     public void onResponse(JSONArray response)
                     {
                        Log.d(TAG, "onResonse " + response.toString());
                     }
                 },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e(TAG, "onErrorResponse: ", error);
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }
}
