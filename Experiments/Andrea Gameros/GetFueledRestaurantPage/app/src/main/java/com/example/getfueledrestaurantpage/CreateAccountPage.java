package com.example.getfueledrestaurantpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class CreateAccountPage extends AppCompatActivity
{
    private EditText Email;
    private EditText Password;
    private EditText Password2;
    private Button CreateAccount;
    private Button SigninHere;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_page);

        Email = (EditText)findViewById(R.id.etEmail);
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
                Intent newIntent = new Intent(view.getContext(), LoginPage.class);
                startActivity(newIntent);
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

    private void createAccount(String email, String password)
    {

    }

    private void checkPassword(String password, String password2)
    {
        //then store all information to database
        if(password.equals(password2))
        {

        }
        //send error message
        else
        {

        }
    }

    private void alert(String message)
    {
       // AlertDialog dlg = new AlertDialogueBuilder(CreateAccountPage.this)
    }
}