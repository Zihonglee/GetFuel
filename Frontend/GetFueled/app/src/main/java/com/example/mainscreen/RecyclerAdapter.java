package com.example.mainscreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//@author- Andrea Gameros
public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.RecyclerViewHolder>
{
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_LIST = 1;

    ArrayList<Restaurant> arrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Restaurant>arrayList)
    {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if(viewType == TYPE_HEAD)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout, parent, false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, viewType);
            return recyclerViewHolder;
        }

        else if(viewType == TYPE_LIST)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, viewType);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {
        if(holder.viewType == TYPE_LIST)
        {
            Restaurant restaurant = arrayList.get(position - 1);
            holder.Name.setText(restaurant.getName());
            holder.Cuisine.setText(restaurant.getCuisine());
            holder.Rating.setText(String.valueOf(restaurant.getRating()));
        }
    }

    @Override
    public int getItemCount()
    {
        return arrayList.size() + 1;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView Name;
        TextView Cuisine;
        TextView Rating;
        int viewType;

        public RecyclerViewHolder(View view, int viewType)
        {
            super(view);

            if(viewType == TYPE_LIST)
            {
                Name = (TextView) view.findViewById(R.id.name);
                Cuisine = (TextView) view.findViewById(R.id.cuisine);
                Rating = (TextView) view.findViewById(R.id.rating);
                this.viewType = TYPE_LIST;
            }

            else if(viewType == TYPE_HEAD)
            {
                this.viewType = TYPE_HEAD;
            }
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position == 0)
        {
            return TYPE_HEAD;
        }
        return TYPE_LIST;
    }
}
