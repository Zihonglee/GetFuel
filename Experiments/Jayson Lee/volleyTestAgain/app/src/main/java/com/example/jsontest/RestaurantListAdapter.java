package com.example.jsontest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class RestaurantListAdapter extends ArrayAdapter<Restaurant> {

    private Context mContext;
    int mResource;

    public RestaurantListAdapter(Context c , int resource, ArrayList<Restaurant> objects){
        super(c, resource, objects);
        mContext = c;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        String id = getItem(position).getId();
        String name = getItem(position).getName();

        Restaurant restaurant = new Restaurant (id, name);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView idName = (TextView) convertView.findViewById(R.id.restaurantIdData);
        TextView restName = (TextView) convertView.findViewById(R.id.restaurantNameData);

        idName.setText(id);
        restName.setText(name);

        return convertView;
    }

}
