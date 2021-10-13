package com.example.loginscreendemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

//this is if user enters login info right and they s to enter the app
public class SecondActivity extends AppCompatActivity
{
    private Button Click;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Click = (Button) findViewById(R.id.btnClick);

        Click.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openActivity2();
            }
        });
    }

        public void openActivity2()
        {
            Intent newIntent = new Intent(SecondActivity.this, ThirdActivity3.class);
            startActivity(newIntent);
        }


        /**
        Click.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        Button btn = (Button)findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(TestActivity.this,second.class));

            }
        });
         **/
}