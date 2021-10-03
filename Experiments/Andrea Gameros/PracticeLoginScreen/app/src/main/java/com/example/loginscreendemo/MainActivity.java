package com.example.loginscreendemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Info;
    private int counter = 3; //number of login attempts

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        Info = (TextView)findViewById(R.id.tvInfo);

        Info.setText("Number of attempts remaining: 3");

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkInfo(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void checkInfo(String userName, String userPassword)
    {
        if((userName.equals("userName")) && (userPassword.equals("1111")))
        {
            //need to let user enter app
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }

        //if info wrong, let user know
        else
        {
            counter--;

            Info.setText("Number of attempts remaining: " + String.valueOf(counter));

            //disable button
            if(counter == 0)
            {
                Login.setEnabled(false);
            }
        }

    }
}
