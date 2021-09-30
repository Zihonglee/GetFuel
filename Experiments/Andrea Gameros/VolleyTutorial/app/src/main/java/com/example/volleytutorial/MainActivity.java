package com.example.volleytutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.view.View;

import org.json.JSONObject;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getRequest, postRequest;
        EditText nameEditText, emailEditText;

        //assigns buttons and texts to their ids created in activity
        getRequest = findViewById(R.id.get_request_btn);
        postRequest = findViewById(R.id.post_request_btn);
        nameEditText = findViewById(R.id.email_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);

        //identifies which button was clicked
        getRequest.setOnClickListener(new View.OnClickListener()
        {
            /**
            @Override
            public void onClick(View view)
            {
                new ServerClass().sendGETRequestsToServer(getApplicationContext(), Constants.url,
                        new ServerResponseCallback() {
                            @Override
                            public void onJSONResponse(JSONObject jsonObject) {

                            }

                            @Override
                            public void onJSONArrayResponse(JSONArray jsonArray) {

                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });
            }
        });
        **/
        postRequest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }
}