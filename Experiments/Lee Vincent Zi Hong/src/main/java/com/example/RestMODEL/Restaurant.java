package com.example.RestMODEL;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Restaurant
{
	@Id
	private final UUID id;
	@Column(name="name")
	private final String name;
	@Column(name="price")
	private final double price;
	@Column(name="rating")
	private final double rating;
	@Column(name="cusineID")
	private final int cuisineID;
	@Column(name="reviewID")
	private final int reviewID;
	
	public Restaurant(UUID id, String name, double price, double rating, int cuisineID, int reviewID)
	{
		this.id = UUID.randomUUID();
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.cuisineID = cuisineID;
		this.reviewID = reviewID;
	}

	public UUID getId()
	{
		return id;
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