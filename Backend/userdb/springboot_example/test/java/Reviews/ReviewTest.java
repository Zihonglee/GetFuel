package Reviews;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import onetoone.Cuisine.Cuisine;
import onetoone.Restaurants.Restaurant;
import onetoone.Reviews.Review;

class ReviewTest 
{

	@Test
	void test() 
	{
		when(repo.getById((long) 1)).thenReturn(new Restaurant("Pizza", "$10.00", "7.5", new Cuisine("Japanese"), "https://www.google.com/search?client=firefox-b-1-d&q=welcome"));

		Restaurant rest = restService.getRestaurantById((long) 1);
		
		List<Review> reviews = new ArrayList<>();
		rest.setReviews(reviews);
		Review review1 = new Review("Well done");
		Review review2 = new Review("good work");
		rest.addReviews(review1);
		rest.addReviews(review2);

		assertEquals("Pizza", rest.getName());
		assertEquals("$10.00", rest.getPrice());
		assertEquals("7.5", rest.getRating());
		assertEquals("https://www.google.com/search?client=firefox-b-1-d&q=welcome", rest.getUrl());
		
		assertEquals("well done", rest.getReviews().get(0));
		assertEquals("good work", rest.getReviews().get(1));
		assertEquals(null, rest.getReviews().get(2));
	}

}
