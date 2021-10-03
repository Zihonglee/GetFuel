package com.example.addingtwonumberspracticeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private EditText num1; //creates variable for the first number to be added
    private EditText num2; //creates variable for the second number to be added
    private Button add;  //creates variable for the add button
    private TextView result;  //creates variable for the result

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sets variables with corresponding id in xml layout
        num1 = (EditText) findViewById(R.id.etNum1);
        num2 = (EditText) findViewById(R.id.etNum2);
        add = (Button) findViewById(R.id.btnAdd);
        result = (TextView)  findViewById(R.id.tvAnswer);

        //add two numbers when add button is clicked
        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int number1 = Integer.parseInt(num1.getText().toString());
                int number2 = Integer.parseInt(num2.getText().toString());
                int sum = number1 + number2;
                result.setText("Answer: " + String.valueOf(sum));
            }
        });

    }
}