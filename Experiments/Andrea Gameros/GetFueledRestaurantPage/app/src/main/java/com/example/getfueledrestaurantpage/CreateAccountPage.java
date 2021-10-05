package com.example.getfueledrestaurantpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;


public class CreateAccountPage extends AppCompatActivity
{
    private EditText Email;
    private EditText UserName;
    private EditText Password;
    private EditText Password2;
    private Button CreateAccount;
    private Button SigninHere;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_page);

        Email = (EditText)findViewById(R.id.etEmail);
        UserName = (EditText)findViewById(R.id.etUserName);
        Password = (EditText)findViewById(R.id.etPassword);
        Password2 = (EditText)findViewById(R.id.etPassword2);
        CreateAccount = (Button)findViewById(R.id.btnCreateAccount);
        SigninHere = (Button)findViewById(R.id.btnSigninHere);

        //stores info into database
        CreateAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Intent newIntent = new Intent(view.getContext(), LoginPage.class);
                //startActivity(newIntent);
                createAccount(Email.getText().toString(),UserName.getText().toString(), Password.getText().toString(), Password2.getText().toString());
            }
        });

        SigninHere.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent newIntent = new Intent(view.getContext(), LoginPage.class);
                startActivity(newIntent);
            }
        });
    }

    private void createAccount(String email, String username, String password, String password2)
    {
        //sends alert
        if(checkPassword(password, password2) == false)
        {
            Toast.makeText(CreateAccountPage.this,"Passwords don't match", Toast.LENGTH_LONG).show();
        }

        else
        {
            //Toast.makeText(CreateAccountPage.this, "test", Toast.LENGTH_LONG).show();
            PostRequest();
        }
    }

    private boolean checkPassword(String password, String password2)
    {
        //then store all information to database
        if(password.equals(password2))
        {
            return true;
        }
        //send error message
        return false;
    }

    private void PostRequest()
    {
        String url = "https://8710b90a-ebe0-4f8f-956e-5c6998590fe8.mock.pstmn.io/Post"; //url for info
        //creates string request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        Toast.makeText(CreateAccountPage.this, "Account created", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(CreateAccountPage.this, "Failed to create account", Toast.LENGTH_LONG).show();
            }
        })
        {
            //code to execute, add parameters to request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("username", UserName.getText().toString());
                params.put("email", Email.getText().toString());
                params.put("password", Password.getText().toString());

                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(CreateAccountPage.this);
        requestQueue.add(jsonArrayRequest);
    }
}