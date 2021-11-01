package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginScreen extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Info;
    private int counter = 3; //number of login attempts
    private Button CreateAccount;
    private RequestQueue requestQueue;
    private TextView msgResponse;
    boolean accountFound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        Name = (EditText)findViewById(R.id.etUserName);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        Info = (TextView)findViewById(R.id.tvInfo);
        CreateAccount = (Button)findViewById(R.id.btnCreateAccount);
        msgResponse = (TextView)findViewById(R.id.msgResponse);

        Info.setText("Number of attempts remaining: 3");

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //checkInfo(Name.getText().toString(), Password.getText().toString());
                //jsonParse(Name.getText().toString(), Password.getText().toString());
                getUsers();
            }
        });

        CreateAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Intent newIntent = new Intent()
                Intent newIntent = new Intent(view.getContext(), CreateAccountScreen.class);
                startActivity(newIntent);
            }
        });
    }

    private void getUsers() {
        RequestQueue queue = Volley.newRequestQueue(this);
        //boolean accountFound = false;

        String url = "https://8710b90a-ebe0-4f8f-956e-5c6998590fe8.mock.pstmn.io/Post";
        //String url = "http://coms-309-059.cs.iastate.edu:8080/user";

        String name = Name.getText().toString();
        String password = Password.getText().toString();


        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject users = response.getJSONObject(i);
                                String usernameJ = users.getString("username");
                                String emailJ = users.getString("email");
                                String passwordJ = users.getString("password");

                                if (name.equals(usernameJ) && password.equals(passwordJ)) {
                                    int t = 1;
                                    msgResponse.append(usernameJ + ", " + passwordJ + "\n\n");
                                    Toast.makeText(LoginScreen.this, "Account found!", Toast.LENGTH_SHORT).show();
                                    accountFound = true;
                                    Intent newIntent = new Intent(LoginScreen.this, HomeScreen.class);
                                    startActivity(newIntent);
                                }
                            }

                            if (accountFound == false) {
                                Toast.makeText(LoginScreen.this, "Account not found", Toast.LENGTH_LONG).show();
                                counter--;

                                Info.setText("Number of attempts remaining: " + String.valueOf(counter));

                                //disable button
                                if (counter == 0) {
                                    Login.setEnabled(false);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        queue.stop();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginScreen.this, "Account not found", Toast.LENGTH_LONG).show();
                error.printStackTrace();
                queue.stop();
            }
        });

        queue.add(jsonRequest);
    }
}