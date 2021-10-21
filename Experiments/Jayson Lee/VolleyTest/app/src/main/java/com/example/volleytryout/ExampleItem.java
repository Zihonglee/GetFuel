package com.example.volleytryout;

public class ExampleItem {
    private String mImageUrl;
    private String mRestaurant;
    private String mPrice;

    public ExampleItem(String imageUrl, String restaurant, String price){
        mImageUrl = imageUrl;
        mRestaurant = restaurant;
        mPrice = price;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public String getRestaurant(){
        return mRestaurant;
    }

    public String getPrice(){
        return mPrice;
    }
}
