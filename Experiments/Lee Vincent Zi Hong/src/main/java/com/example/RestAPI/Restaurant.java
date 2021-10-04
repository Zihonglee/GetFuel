package com.example.RestAPI;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import coms.example.CuisineAPI.Cuisine;
import coms.example.ReviewsAPI.Review;


@Entity
public class Restaurant
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	private double rating;
	
    @ManyToOne
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    @OneToMany
    @JoinColumn(name ="review_id")
    private List<Review> reviews;
    
	public Restaurant(){
	}
	
	public Restaurant(String name, double price, double rating, Cuisine cuisine)
	{
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.cuisine = cuisine;
		reviews = new ArrayList<>();
	}

	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

    public void setName(String restName) 
    {
        this.name = restName;
    }

	public double getPrice() 
	{
		return price;
	}

    public void setPrice(double price) {
        this.price = price;
    }

	public double getRating() 
	{
		return rating;
	}
	

    public void setRating(double rating) {
        this.rating = rating;
    }

	public Cuisine getCuisine()
	{
		return cuisine;
	}

	public List<Review> getReviews() 
	{
		return reviews;
	}

    public void setReviews(List<Review> reviews) 
    {
        this.reviews = reviews;
    }

    public void addReviews(Review review)
    {
        this.reviews.add(review);
    }

	@Override
	public String toString()
	{
        return "id: " + getId() + ",/n name: " + getName() + "/n price: " + getPrice()
        + "rating: " + getRating() + ",/n cuisineID: " + getCuisine() + "/n reviewID: " + getReviews();  
    }
}