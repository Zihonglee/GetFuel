package onetoone.Restaurants;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>
{
	public Restaurant findRestaurantById(Long Id);
	
	@Transactional
	public void deleteRestaurantById(Long Id);
}
