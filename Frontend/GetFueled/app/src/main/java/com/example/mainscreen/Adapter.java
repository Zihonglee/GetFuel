package com.example.mainscreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private LayoutInflater inflater;
    private List<Restaurant> restaurants;
    //OnNoteListener mOnNoteListener;

    public Adapter(Context ctx, List<Restaurant> restaurants)
    {
        this.inflater = LayoutInflater.from(ctx);
        this.restaurants = restaurants;
        //this.mOnNoteListener = onNoteListener; //added
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.storage, parent, false);
        return new ViewHolder(view); // mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        //bind the data
        holder.restaurantName.setText(restaurants.get(position).getName());
        holder.restaurantCuisine.setText(restaurants.get(position).getCuisine());
        holder.restaurantRating.setText(String.valueOf(restaurants.get(position).getRating()));
        //Picasso.get().load(restaurants.get(position.getCoverImage().into(holder.restaurantImg);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RestaurantScreen.class);
                //intent.putExtra("name", restaurants.get(position).getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return restaurants.size();
    }

    //added implements...
    public class ViewHolder extends RecyclerView.ViewHolder //implements View.OnClickListener
    {
        TextView restaurantName;
        TextView restaurantCuisine;
        TextView restaurantRating;
        //ConstraintLayout mainLayout;
        //ImageView restaurantImg;
        //OnNoteListener onNoteListener; //added

        public ViewHolder(View itemView) //OnNoteListener onNoteListener)
        {
            super(itemView);

            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantCuisine = itemView.findViewById(R.id.restaurantCuisine);
            restaurantRating = itemView.findViewById(R.id.restaurantRating);
            //restaurantImg = itemView.findViewById(R.id.restaurantImg);
            //this.onNoteListener = onNoteListener; //added

            //itemView.setOnClickListener(this); //added
        }

    }//delete if reimplement override

        /**
        @Override
        public void onClick(View view)
        {
            onNoteListener.onNoteClick(getAdapterPosition());
            //itemView.setOnClickListener(this);
        }
    }


    public interface OnNoteListener
    {
        void onNoteClick(int position);
    }


    /**
        //added everything below
        @Override
        public void onClick(View view)
        {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }



    public interface OnNoteListener
    {
        void onNoteClick(int position);
    }

    **/
}
