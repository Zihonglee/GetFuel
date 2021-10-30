package com.example.mainscreen;

//@author-Andrea Gameros
public class Restaurant
{
    private String name;
    private String cuisine;
    private double rating;

   public Restaurant(String name, String cuisine, double rating)
   {
        this.setName(name);
        this.setCuisine(cuisine);
        this.setRating(rating);
   }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCuisine()
    {
        return cuisine;
    }

    public void setCuisine(String cuisine)
    {
        this.cuisine = cuisine;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(Double rating)
    {
        this.rating = rating;
    }
}
