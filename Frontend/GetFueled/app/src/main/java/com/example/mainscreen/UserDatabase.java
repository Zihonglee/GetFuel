package com.example.mainscreen;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;

public class UserDatabase extends AppCompatActivity
{

    RecyclerView recyclerView;
    List<User> users;
    private static String JSON_URL = "http://coms-309-059.cs.iastate.edu:8080/user";
    UserAdapter adapter;

    Button addUser;

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_database);

        recyclerView = findViewById(R.id.recyclerViewUsers);
        users = new ArrayList<>();

        //extractUsers();

        addUser = (Button) findViewById(R.id.addUserBtn);

        addUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(UserDatabase.this, AddUserScreen.class);
                startActivity(intent);
            }
        });
    }

    private void extractUsers()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                try
                {
                    for(int i = 0; i < response.length(); i++)
                    {
                        JSONObject userObject = response.getJSONObject(i);

                        User user = new User();
                        user.setUserName(userObject.getString("name"));
                        user.setId(userObject.getString("id"));
                        user.setRole(userObject.getString("roleType"));
                        users.add(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new UserAdapter(getApplicationContext(),users);
                recyclerView.setAdapter(adapter);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserDatabase.this, "Error", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        queue.add(jsonArrayRequest);
    }
}