package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.role.RoleManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddUserScreen extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private EditText Username;
    private EditText Email;
    private EditText Password;
    private AutoCompleteTextView RoleSelect;
    private Button Add;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> roleList= new ArrayList<String>();
    private String Role;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_screen);

        Username = (EditText) findViewById(R.id.aued_username);
        Email = (EditText) findViewById(R.id.aued_email);
        Password = (EditText) findViewById(R.id.aued_password);
        RoleSelect = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        Add = (Button) findViewById(R.id.au_btnAdd);

        getRoleTypes();

        RoleSelect.setAdapter(arrayAdapter);
        RoleSelect.setOnItemClickListener(this);

        Add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                createAccount();
            }
        });
    }

    private void createAccount()
    {
        String username = Username.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();

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
            object.put("roleType", Role);
            object.put("name", username);
            object.put("email", email);
            object.put("password", password);
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

    public void getRoleTypes()
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://32d715de-7aad-4668-ae7a-e709501daf7a.mock.pstmn.io/roles";



        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        printResult.setText(response.toString());
                        try {
                            for(int i = 0; i < response.length(); i++)
                            {

                                JSONObject roles = response.getJSONObject(i);
                                String role = roles.getString("roleType");

                                roleList.add(role);

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

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_item, roleList);
        RoleSelect.setAdapter(arrayAdapter);


        queue.add(jsonRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        // fetch the user selected value
        String item = adapterView.getItemAtPosition(i).toString();
        Role = item;

        // create Toast with user selected value
        //Toast.makeText(AddUserScreen.this, "Selected Item is: \t" + item, Toast.LENGTH_LONG).show();

        // set user selected value to the TextView
        //tvDisplay.setText(item);
    }
}