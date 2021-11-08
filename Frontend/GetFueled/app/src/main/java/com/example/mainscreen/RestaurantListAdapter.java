package com.example.mainscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * Ignore this class. It was going to be used for the reycvle view restaurant adpater but didn't work.
 * Replaced by Adapter class. This needs to be deleted.
 */
public class RestaurantListAdapter extends ArrayAdapter<Restaurant> {

    private ArrayList<Restaurant> restaurantList;
    int mResource;

    public RestaurantListAdapter(Context c , int resource, ArrayList<Restaurant> restaurantList){
        super(c, resource);
        this.restaurantList = restaurantList;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        int phraseIndex = position;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.databaserow,parent, false);
        }

        TextView idName =  convertView.findViewById(R.id.restaurantIdData);
        TextView restName = convertView.findViewById(R.id.restaurantNameData);

        idName.setText(restaurantList.get(position).getId());
        restName.setText(restaurantList.get(position).getName());

        return convertView;
    }

}
