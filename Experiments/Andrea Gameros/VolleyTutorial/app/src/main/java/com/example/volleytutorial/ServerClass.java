package com.example.volleytutorial;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.URL;

public class ServerClass
{
    final static String TAG = ServerClass.class.getName();

    //makes requests
    public void  sendPOSTRequestsToServer(Context context, JSONObject jsonObject, final ServerResponseCallback serverResponseCallback)
    {
        //queue for requests
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JSONObject(
                Request.Method.POST,
                URL,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, msg:"onResponse: "+response.toString());
                    }
                }
                );
    }
}
