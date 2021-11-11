package Reviews;

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
import onetoone.Restaurants.Restaurant;
import onetoone.Restaurants.RestaurantRepository;
import onetoone.Reviews.Review;
import onetoone.Reviews.ReviewController;
import onetoone.Reviews.ReviewRepository;
import onetoone.Users.User;
import onetoone.Users.UserRepository;


public class ReviewTest 
{
	@InjectMocks
	ReviewController reviewService;

	@Mock
	ReviewRepository repo;
	
	@Mock
	RestaurantRepository restRepo;
	
	@Mock
	UserRepository userRepository;
	
	@SuppressWarnings("deprecation") //not needed
	@Before
	public void init() 
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getReviewstest()
	{
		when(repo.getReviewById(Long.valueOf(1))).thenReturn(new Review("This is a good restaurant."));

		Review reviews = reviewService.getReviewById(Long.valueOf(1));
		assertEquals("This is a good restaurant.", reviews.getComments());
		
		verify(repo, times(1)).getReviewById(anyLong());
	}

	@Test
	public void deleteReviewTest()
	{
		verify(repo, never()).deleteReviewById(anyLong());
		when(repo.getReviewById(Long.valueOf(1))).thenReturn(new Review("Well done"));
		
		doNothing().when(repo).deleteReviewById(Long.valueOf(1));
		String comments = reviewService.deleteReview(Long.valueOf(1));
		
		assertEquals("Deleted successfully", comments);
		
		verify(repo, atMost(1)).getReviewById(anyLong()); //since there is only one line of getreviewbyid that i mocked
		verify(repo, atMost(1)).deleteReviewById(anyLong());
	}
	
	@Test
	public void deleteReviewRestaurantTest()
	{
		List<Review> listOfReview = new ArrayList<>();
		when(repo.getReviewById(Long.valueOf(1))).thenReturn(new Review("Well Done"));
		when(restRepo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Thai kitchen", "$10.00", "7.00", null, "https://www.thaikitchenames.com/"));
		when(userRepository.getUserById(Long.valueOf(1))).thenReturn(new User("testing", "testing@gmail.com", "Unknown", "user"));
		userRepository.getUserById(Long.valueOf(1)).setReview(new ArrayList<>());
		reviewService.assignReviews(Long.valueOf(1), repo.getReviewById(Long.valueOf(1)), Long.valueOf(1));
		//continue here test the 
	}

	@Test
	public void getAllReviewTest() 
	{
		List<Review> list = new ArrayList<Review>();
		
		Review review1 = new Review("cool restaurant");
		Review review2 = new Review("nice restaurant");
		Review review3 = new Review("awesome restaurant");
		Review review4 = new Review("beautiful restaurant");
		Review review5 = new Review("wonderful restaurant");

		list.add(review1);
		list.add(review2);
		list.add(review3);
		list.add(review4);
		list.add(review5);

		when(reviewService.getAllReview()).thenReturn(list);

		List<Review> reviewList = reviewService.getAllReview();

		assertEquals(5, reviewList.size());
		
		verify(repo, never()).getReviewById(anyLong());
	}
	
	@Test
	public void createReviewTest()
	{		
		Review reviews = null;
		String ReviewTest = reviewService.createReview(reviews);
		assertEquals("failure", ReviewTest);

		reviews = new Review("Nice Restaurant");
		ReviewTest = reviewService.createReview(reviews);
		assertEquals("success", ReviewTest);		
	}
	
	@Test
	public void updateTest()
	{
		Review test = new Review("Cool Restaurant");
		when(repo.getReviewById(Long.valueOf(2))).thenReturn(test);
		assertEquals(test, reviewService.getReviewById(Long.valueOf(2)));
		
		Review newtest = new Review("Awesome Restaurant");
		reviewService.updateReview(Long.valueOf(2), newtest);
		
		assertEquals(newtest.getComments(), repo.getReviewById(Long.valueOf(2)).getComments());	
		
		verify(repo, times(3)).getReviewById(anyLong()); //running in class method as well
	}
	
	@Test
	public void assignNullReviewsTest()
	{
		when(restRepo.getRestaurantById(Long.valueOf(1))).thenReturn(null);
		when(userRepository.getUserById(Long.valueOf(1))).thenReturn(null);
		when(repo.getReviewById(Long.valueOf(1))).thenReturn(new Review("Well Done"));
		
		String output = reviewService.assignReviews(Long.valueOf(1), repo.getReviewById(Long.valueOf(1)), Long.valueOf(1));
		assertEquals("failure", output);
		
		when(restRepo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Thai kitchen", "$10.00", "7.00", null, "https://www.thaikitchenames.com/"));
		output = reviewService.assignReviews(Long.valueOf(1), repo.getReviewById(Long.valueOf(1)), Long.valueOf(1)); //since one of them is still null
		assertEquals("failure", output);

		when(userRepository.getUserById(Long.valueOf(1))).thenReturn(new User("testing", "testing@gmail.com", "Unknown", "user"));
		when(restRepo.getRestaurantById(Long.valueOf(1))).thenReturn(null);
		output = reviewService.assignReviews(Long.valueOf(1), repo.getReviewById(Long.valueOf(1)),Long.valueOf(1)); //since one of them is still null
		assertEquals("failure", output);
		
		//verify need to do
	}
	
	@Test
	public void assinReviewsTest()
	{
		when(repo.getReviewById(Long.valueOf(1))).thenReturn(new Review("Well Done"));
		when(restRepo.getRestaurantById(Long.valueOf(1))).thenReturn(new Restaurant("Thai kitchen", "$10.00", "7.00", null, "https://www.thaikitchenames.com/"));
		when(userRepository.getUserById(Long.valueOf(1))).thenReturn(new User("testing", "testing@gmail.com", "Unknown", "user"));
		userRepository.getUserById(Long.valueOf(1)).setReview(new ArrayList<>());
		
		String output = reviewService.assignReviews(Long.valueOf(1), repo.getReviewById(Long.valueOf(1)), Long.valueOf(1));
		assertEquals("success", output);
		
		List<Review> listOfReview = new ArrayList<>();
		listOfReview.add(repo.getReviewById(Long.valueOf(1)));
		assertEquals(listOfReview, restRepo.getRestaurantById(Long.valueOf(1)).getReviews());
		assertEquals(listOfReview, userRepository.getUserById(Long.valueOf(1)).getAllReviews());
		//since there is only one comment by the user, then it will only return that otherwise it will include other more review
		
		//verify need to do
	}
}
