package com.example.RestMODEL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Restaurant
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	private double rating;
	private int cuisineID;
	private int reviewID;
	
	public Restaurant(){
	}
	
	public Restaurant(Long id, String name, double price, double rating, int cuisineID, int reviewID)
	{
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.cuisineID = cuisineID;
		this.reviewID = reviewID;
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

	public double getPrice() 
	{
		return price;
	}

	public double getRating() 
	{
		return rating;
	}

	public int getCuisineID()
	{
		return cuisineID;
	}

	public int getReviewID() 
	{
		return reviewID;
	}

	@Override
	public String toString()
	{
        return "id: " + getId() + ",/n name: " + getName() + "/n price: " + getPrice()
        + "rating: " + getRating() + ",/n cuisineID: " + getCuisineID() + "/n reviewID: " + getReviewID();  
    }
}