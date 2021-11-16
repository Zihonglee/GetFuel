package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserDatabase extends AppCompatActivity {

    Button addUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_database);

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
}