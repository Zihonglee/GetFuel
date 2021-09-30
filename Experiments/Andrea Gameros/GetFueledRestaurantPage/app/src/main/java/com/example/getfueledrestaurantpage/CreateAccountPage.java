package com.example.getfueledrestaurantpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccountPage extends AppCompatActivity
{
    private Button CreateAccount;
    private Button SigninHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_page);

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
}
