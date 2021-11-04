package com.example.mainscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchScreen extends AppCompatActivity
{

    private EditText Search;
    private Button SearchButton;
    private SearchView searchView;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    ArrayList<String> cuisineList = new ArrayList<String>();
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    //@Author - Jayson Lee
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);


        searchView = findViewById(R.id.searchBar);
        listView = findViewById(R.id.listName);

//        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(arrayAdapter);
        restaurantGetRequest();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchScreen.this.arrayAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchScreen.this.arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

        dl = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int id = item.getItemId();

                if(id == R.id.home)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(SearchScreen.this, "Home", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SearchScreen.this, HomeScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.search)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(SearchScreen.this, "Search", Toast.LENGTH_SHORT).show();
                }

                if(id == R.id.map)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(SearchScreen.this, "Map", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SearchScreen.this, MapScreen.class);
                    startActivity(intent);
                }

                if(id == R.id.foodpicker)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(SearchScreen.this, "FoodPicker", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(HomeScreen.this, FoodPickerPage.class);
                    //startActivity(intent);
                }

                if(id == R.id.addRestaurant)
                {
                    Toast.makeText(SearchScreen.this, "Add Restaurant", Toast.LENGTH_SHORT).show();
                }

                if(id == R.id.logout)
                {
                    //toast provides simple feedback about an operation of a small popup
                    Toast.makeText(SearchScreen.this, "Logout", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SearchScreen.this, LoginScreen.class);
                    startActivity(intent);
                }


                return true;
            }
        });
    }

    //@Author - Jayson Lee
    private void restaurantGetRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://coms-309-059.cs.iastate.edu:8080/cuisines";



        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i = 0; i < response.length(); i++) {

                                JSONObject cuisines = response.getJSONObject(i);

                                String cuisine = cuisines.getString("cuisineType");

                                cuisineList.add(cuisine);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        queue.stop();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                queue.stop();
            }
        });
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, cuisineList);
        listView.setAdapter(arrayAdapter);
        queue.add(jsonRequest);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}