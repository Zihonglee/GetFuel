package com.example.volleytryout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;

    public MyAdapter(Context context, ArrayList<ExampleItem> exampleList){
        mContext = context;
        mExampleList = exampleList;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.restaurant,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String restaurant = currentItem.getRestaurant();
        String price = currentItem.getPrice();

        holder.mTextViewRestaurant.setText(restaurant);
        holder.mTextViewPrice.setText("Price" + price);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextViewRestaurant;
        public TextView mTextViewPrice;


        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewRestaurant = itemView.findViewById(R.id.restaurantName);
            mTextViewPrice = itemView.findViewById(R.id.restaurantPrice);

        }
    }
}
