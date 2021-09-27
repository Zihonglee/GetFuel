package com.example.volleytutorial;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ServerResponseCallback
{
    public void onJSONResponse(JSONObject jsonObject);
    public void onJS0NArrayResponse(JSONArray jsonArray);
    public void onJS0NResponse(Exception e);
}
