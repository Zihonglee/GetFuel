package Restaurant;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import onetoone.Cuisine.Cuisine;
import onetoone.Cuisine.CuisineRepository;
import onetoone.Restaurants.Restaurant;
import onetoone.Restaurants.RestaurantController;
import onetoone.Restaurants.RestaurantRepository;
import onetoone.Reviews.Review;
import onetoone.Reviews.ReviewController;
import onetoone.Reviews.ReviewRepository;
import onetoone.Users.User;
import onetoone.Users.UserRepository;

public class RestTest
{
	@InjectMocks
	RestaurantController restService;

	@InjectMocks
	ReviewController reviewService;
	
	@Mock
	CuisineRepository crepo;

	@Mock
	RestaurantRepository repo;
	
	@Mock
	UserRepository userRepository;

	@Mock
	ReviewRepository reviewRepository;

	@SuppressWarnings("deprecation") //not needed
	@Before
	public void init() 
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getRestaurantTest()
	{
		assertNull(repo.getRestaurantById(Long.valueOf(1)));
		when(repo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Pizza", "$10.00", "7.5", new Cuisine("Japanese"), "https://www.google.com/search?client=firefox-b-1-d&q=pizza"));

		Restaurant rest = restService.getRestaurantById(Long.valueOf(1));

		assertEquals("Pizza", rest.getName());
		assertEquals("$10.00", rest.getPrice());
		assertEquals("7.5", rest.getRating());
		assertEquals("https://www.google.com/search?client=firefox-b-1-d&q=pizza", rest.getUrl());
		
		verify(repo, times(2)).getRestaurantById(Long.valueOf(1));
		verify(repo, times(2)).getRestaurantById(anyLong());
	}
	
	@Test
	public void deleteRestaurantTest()
	{
		verify(repo, never()).deleteRestaurantById(anyLong());
		List<Restaurant> emptylist = new ArrayList<>();
		List<Review> emptylistReview = new ArrayList<>();
		
		when(repo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Pizza", "$10.00", "7.5", null, "https://www.google.com/search?client=firefox-b-1-d&q=pizza"));
		doNothing().when(repo).deleteRestaurantById(Long.valueOf(1));
		assertEquals(restService.getAllRestaurant(), emptylist);		
		
		when(repo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Pizza", "$10.00", "7.5", new Cuisine("Japanese"), "https://www.google.com/search?client=firefox-b-1-d&q=pizza"));
		doNothing().when(repo).deleteRestaurantById(Long.valueOf(1));
		assertEquals(restService.getAllRestaurant(), emptylist);
		
		when(userRepository.getUserById(Long.valueOf(1))).thenReturn(new User("testing", "testing@gmail.com", "Unknown", "user"));
		when(userRepository.getUserById(Long.valueOf(2))).thenReturn(new User("testingnextaccount", "testing2@gmail.com", "Unknown", "user"));
		when(reviewRepository.getReviewById(Long.valueOf(1))).thenReturn(new Review("Nice Restaurant"));
		when(reviewRepository.getReviewById(Long.valueOf(2))).thenReturn(new Review("Cool Restaurant"));
		when(reviewRepository.getReviewById(Long.valueOf(3))).thenReturn(new Review("Awesome Restaurant"));
		when(reviewRepository.getReviewById(Long.valueOf(4))).thenReturn(new Review("Cool and Nice Restaurant"));
		when(repo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Pizza", "$10.00", "7.5", new Cuisine("Japanese"), "https://www.google.com/search?client=firefox-b-1-d&q=pizza"));

		User user1 = userRepository.getUserById(Long.valueOf(1));
		user1.setReview(new ArrayList<>());
		User user2 = userRepository.getUserById(Long.valueOf(2));
		user2.setReview(new ArrayList<>());
		userRepository.save(user1);
		userRepository.save(user2);
		Review reviews = reviewRepository.getReviewById(Long.valueOf(1));
		reviewService.assignReviews(Long.valueOf(1), reviews, Long.valueOf(1));
		Review reviews2 = reviewRepository.getReviewById(Long.valueOf(2));
		reviewService.assignReviews(Long.valueOf(1), reviews2, Long.valueOf(1));
		Review reviews3 = reviewRepository.getReviewById(Long.valueOf(3));
		reviewService.assignReviews(Long.valueOf(1), reviews3, Long.valueOf(2));
		Review reviews4 = reviewRepository.getReviewById(Long.valueOf(4));
		reviewService.assignReviews(Long.valueOf(1), reviews4, Long.valueOf(2));
		
		List<Review> listOfOutput = restService.getRestaurantById(Long.valueOf(1)).getReviews();
		assertEquals(listOfOutput.get(0), reviews);
		assertEquals(listOfOutput.get(1), reviews2);
		List<Review> listOfOutput2 = restService.getRestaurantById(Long.valueOf(2)).getReviews();
		assertEquals(listOfOutput2.get(0), reviews3);
		assertEquals(listOfOutput2.get(1), reviews4);
		
		doNothing().when(repo).deleteRestaurantById(Long.valueOf(1));
		assertEquals(restService.getAllRestaurant(), emptylist);
		assertEquals(userRepository.getUserById(Long.valueOf(1)).getAllReviews(), emptylistReview);
		emptylistReview.add(reviews3);
		assertEquals(userRepository.getUserById(Long.valueOf(2)).getAllReviews(), emptylistReview);
		
//		verify(repo, atMost(1)).getRestaurantById(anyLong()); //since there is only one line of getrestaurantbyid that i mocked
//		verify(repo, atMost(1)).deleteRestaurantById(anyLong());
	}

	@Test
	public void getAllRestaurantTest() 
	{
		List<Restaurant> list = new ArrayList<Restaurant>();
		
		Cuisine cs = new Cuisine("Asian");
		Restaurant rest1 = new Restaurant("US Pizza", "$7.00", "8.5", cs, "https://uspizzaco.net/");
		Restaurant rest2 = new Restaurant("sze chuan", "$11.00", "9.0", cs, "https://www.szechuanhouseames.com/");
		Restaurant rest3 = new Restaurant("potbelly", "$6.00", "8.0", cs, "https://www.potbelly.com/");

		list.add(rest1);
		list.add(rest2);
		list.add(rest3);

		when(restService.getAllRestaurant()).thenReturn(list);

		List<Restaurant> restList = restService.getAllRestaurant();

		assertEquals(3, restList.size());
		
		verify(repo, never()).getRestaurantById(anyLong());
	}
	@Test
	public void addRestaurantTest()
	{		
		Restaurant rest = null;
		String RestService = restService.addRestaurant(rest);
		assertEquals("failure", RestService);

		Cuisine cs = new Cuisine("Asian");
		rest = new Restaurant("Thai kitchen", "$10.00", "7.00", cs, "https://www.thaikitchenames.com/");
		RestService = restService.addRestaurant(rest);
		assertEquals("Restaurant saved", RestService);		
	}
	
	@Test
	public void updateTest()
	{
		Cuisine cs = new Cuisine("Asian");
		Restaurant test = new Restaurant("Thai kitchen", "$10.00", "7.00", cs, "https://www.thaikitchenames.com/");
		when(repo.getRestaurantById(Long.valueOf(2))).thenReturn(test);
		assertEquals(test, restService.getRestaurantById(Long.valueOf(2)));

		assertEquals("Thai kitchen", repo.getRestaurantById(Long.valueOf(2)).getName());
		assertEquals("$10.00", repo.getRestaurantById(Long.valueOf(2)).getPrice());
		assertEquals("7.00", repo.getRestaurantById(Long.valueOf(2)).getRating());
		assertEquals("https://www.thaikitchenames.com/", repo.getRestaurantById(Long.valueOf(2)).getUrl());
		
		Restaurant newtest = new Restaurant("Little Taipei", "$10.00", "6.00", cs, "https://www.ameslittletaipei.com/");
		restService.updateRestaurantById(Long.valueOf(2), newtest);

		assertEquals("Little Taipei", repo.getRestaurantById(Long.valueOf(2)).getName());
		assertEquals("$10.00", repo.getRestaurantById(Long.valueOf(2)).getPrice());
		assertEquals("6.00", repo.getRestaurantById(Long.valueOf(2)).getRating());
		assertEquals("https://www.ameslittletaipei.com/", repo.getRestaurantById(Long.valueOf(2)).getUrl());
		
		verify(repo, times(10)).getRestaurantById(anyLong()); //running in class method as well
	}
	
	@Test
	public void assignTest()
	{
		when(repo.getRestaurantById(Long.valueOf(1))).thenReturn(null);
		when(crepo.getCuisineById(Long.valueOf(1))).thenReturn(null);
		String output = restService.assigneCusinetoRest(Long.valueOf(1), Long.valueOf(1));
		assertEquals(output, "failure");
		
		when(crepo.getCuisineById(Long.valueOf(1))).thenReturn(new Cuisine("Japanese"));
		output = restService.assigneCusinetoRest(Long.valueOf(1), Long.valueOf(1)); //since one of them is still null
		assertEquals(output, "failure");

		when(repo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Thai kitchen", "$10.00", "7.00", new Cuisine("Japanese"), "https://www.thaikitchenames.com/"));
		when(crepo.getCuisineById(Long.valueOf(1))).thenReturn(null);
		output = restService.assigneCusinetoRest(Long.valueOf(1), Long.valueOf(1)); //since one of them is still null
		assertEquals(output, "failure");
		
		Cuisine cs = new Cuisine("Chinese");
		when(repo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Thai kitchen", "$10.00", "7.00", null, "https://www.thaikitchenames.com/"));
		when(crepo.getCuisineById(Long.valueOf(1))).thenReturn(cs);

		output = restService.assigneCusinetoRest(Long.valueOf(1), Long.valueOf(1));
		assertEquals(output, "success");
		assertEquals(repo.getRestaurantById(Long.valueOf(1)).getCuisine().getCuisineType(), "Chinese");	
		
		cs = new Cuisine("Japanese");
		when(crepo.getCuisineById(Long.valueOf(2))).thenReturn(cs);
		output = restService.assigneCusinetoRest(Long.valueOf(1), Long.valueOf(2));
		assertEquals(output, "success");
		assertEquals(repo.getRestaurantById(Long.valueOf(1)).getCuisine().getCuisineType(), "Japanese");

//		verify(crepo, times(5)).getCuisineById(anyLong()); //w5
//		verify(repo, times(8)).getRestaurantById(anyLong());
	}
}
