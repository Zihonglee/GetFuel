package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddUserScreen extends AppCompatActivity
{
    private EditText Id;
    private EditText Username;
    private EditText Email;
    private EditText Password;
    private EditText Role;
    private Button Save;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_screen);

        Username = (EditText) findViewById(R.id.aued_username);
        Email = (EditText) findViewById(R.id.aued_email);
        Password = (EditText) findViewById(R.id.aued_password);
        Role = (EditText) findViewById(R.id.aued_role);
        Save = (Button) findViewById(R.id.au_btnSave);

    }

    private void createAccount()
    {
        String username = Username.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        String role = Role.getText().toString();

        requestQueue = Volley.newRequestQueue(this);
        //String url = "https://8710b90a-ebe0-4f8f-956e-5c6998590fe8.mock.pstmn.io/Post";
        String url = "http://coms-309-059.cs.iastate.edu:8080/user";


        abstract class MyJsonArrayRequest extends JsonRequest<JSONArray> {


            public MyJsonArrayRequest(int method, String url, JSONObject jsonRequest,
                                      Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
                super(method, url, (jsonRequest == null) ? null : jsonRequest.toString(), listener,
                        errorListener);
            }
        }

        JSONObject object = new JSONObject();

        try
        {
            object.put("name", username);
            object.put("email", email);
            object.put("password", password);
            object.put("roleType", role);
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }



        MyJsonArrayRequest jsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        Toast.makeText(AddUserScreen.this, "Account created", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(AddUserScreen.this, "Failed to create account", Toast.LENGTH_LONG).show();
            }
        })
        {
            /**
             @Override
             protected Map<String, String> getParams()
             {
             Map<String, String> params = new HashMap<>();
             String u = UserName.getText().toString();
             String e = Email.getText().toString();
             String p = Password.getText().toString();
             msgResponse.append(u + " " + e + " " + p);
             System.out.println(u + " " + e + " " + p);
             params.put("username", UserName.getText().toString());
             params.put("email", Email.getText().toString());
             params.put("password", Password.getText().toString());

             return params;
             }
             **/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

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

        };


        //requestQueue = Volley.newRequestQueue(CreateAccountPage.this);
        requestQueue.add(jsonArrayRequest);
    }

}