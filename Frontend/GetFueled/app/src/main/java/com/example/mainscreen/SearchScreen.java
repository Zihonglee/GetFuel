package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchScreen extends AppCompatActivity {

    private EditText Search;
    private Button SearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
        Search = (EditText)findViewById(R.id.etSearch);
        SearchButton = (Button)findViewById(R.id.btnSearch);

        String SearchInfo = Search.getText().toString();

        //when click search, search options appear
        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });
    }
}