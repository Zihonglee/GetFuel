package onetoone.Restaurants;

import onetoone.Cuisine.Cuisine;
import onetoone.Reviews.Review;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identification of this restaurant", name = "id", required = true, value = "test id")
	private Long id;
	
	@ApiModelProperty(notes = "Name of this restaurant", name = "name", required = true, value = "test name")
	private String name;
	
	@ApiModelProperty(notes = "Average price for this restaurant", name = "price", required = true, value = "test price")
	private String price;
	
	@ApiModelProperty(notes = "rating of this restaurant", name = "rating", required = true, value = "test rating")
	private String rating;
	
	@ApiModelProperty(notes = "URL of this restaurant", name = "Url", required = true, value = "test url")
	private String Url;

	@ManyToOne(targetEntity = Cuisine.class) //cascade = CascadeType.ALL 
	@JsonIgnore
	@ApiModelProperty(notes = "The cuisine of this specific restaurant", name = "cuisine", required = true, value = "test cuisine")
	private Cuisine cuisine;

	@OneToMany(targetEntity = Review.class) //cascade = CascadeType.ALL
	@ApiModelProperty(notes = "The list of reviews of this restaurant", name = "id", required = true, value = "test reviews")
	private List<Review> reviews;

	public Restaurant(){
	}

	public Restaurant(String name, String price, String rating, Cuisine cuisine, String imageUrl)
	{
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.cuisine = cuisine;
		this.Url = imageUrl;
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

	public String getPrice() 
	{
		return price;
	}

	public void setPrice(String price) 
	{
		this.price = price;
	}

	public String getRating() 
	{
		return rating;
	}

	public void setRating(String rating) 
	{
		this.rating = rating;
	}

	public Cuisine getCuisine()
	{
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine)
	{
		this.cuisine = cuisine;
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

	public String getUrl()
	{
		return Url;
	}
	
	public void setUrl(String url)
	{
		this.Url = url;
	}

	@Override
	public String toString()
	{
		return "id: " + getId() + ",/n name: " + getName() + "/n price: " + getPrice()
		+ "rating: " + getRating() + ",/n cuisineID: " + getCuisine() + "/n reviewID: " + getReviews();  
	}
}
