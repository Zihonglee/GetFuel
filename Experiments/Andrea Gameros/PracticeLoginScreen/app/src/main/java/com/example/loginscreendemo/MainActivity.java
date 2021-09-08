package com.example.loginscreendemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button login;
    private TextView Info;
    private int counter = 3; //number of login attempts

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText)findViewById(R.id.etUserName);
        password = (EditText)findViewById(R.id.etPassword);
        login = (Button)findViewById(R.id.btnLogin);
        Info = (TextView)findViewById(R.id.tvInfo);

        Info.setText("Number of attempts remaining: 3");

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkInfo(userName.getText().toString(), password.getText().toString());
            }
        });
    }

    private void checkInfo(String username, String userPassword)
    {
        if((username == "userName") && (userPassword == "1111"))
        {
            //need to let user enter app
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }

        //if info wrong, let user know
        else
        {
            counter--;

            Info.setText("Number of attempts remaining:" + String.valueOf(counter));

            //disable button
            if(counter == 0)
            {
                login.setEnabled(false);
            }
        }

    }
}
