package com.example.mainscreen;

//@author-Andrea Gameros
public class Restaurant
{
    public String id;
    public String name;
    public String cuisine;
    public String rating;

    public Restaurant()
    {

    }

   public Restaurant(String name, String cuisine, String rating)
   {
        this.setName(name);
        this.setCuisine(cuisine);
        this.setRating(rating);
   }

    public Restaurant(String id, String name)
    {
        this.setName(id);
        this.setName(name);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRating()
    {
        return rating;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }
}
