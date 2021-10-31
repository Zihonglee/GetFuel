package com.example.mainscreen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

//@author-Andrea Gameros
public class PullRestaurantList extends AsyncTask<Void, Restaurant, Void>
{
    Context ctx;
    Activity activity;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    ArrayList<Restaurant> arrayList = new ArrayList<>();

    public PullRestaurantList(Context ctx)
    {
        this.ctx = ctx;
        activity = (Activity)ctx;
    }

    //String json_string = "http://coms-309-059.cs.iastate.edu:8080/restaurant";
    String json_string = "https://e29a5922-2c06-41b4-83a8-8141fc23a42b.mock.pstmn.io/restaurants";

    @Override
    protected void onPreExecute()
    {
        recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("One moment...");
        progressDialog.setMessage("Restaurants loading...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        try
        {
            URL url = new URL(json_string);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line+"\n");
            }
            httpURLConnection.disconnect();
            String json_string = stringBuilder.toString().trim();

            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("restaurant_list");
            int count = 0;
            while(count < jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                count++;

                String joNAME = JO.getString("name");
                String joCUISINE = JO.getString("cuisine");
                String joRATING = JO.getString("rating");

                Restaurant restaurant = new Restaurant(joNAME, joCUISINE, joRATING);
                publishProgress(restaurant);
                Thread.sleep(1000);
            }

            Log.d("JSON STRING",json_string);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Restaurant... values)
    {
        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void unused)
    {
        progressDialog.dismiss();
    }
}
