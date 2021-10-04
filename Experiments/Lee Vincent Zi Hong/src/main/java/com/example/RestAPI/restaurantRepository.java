package com.example.RestAPI;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface restaurantRepository extends JpaRepository<Restaurant, Long>
{
	public Restaurant findRestaurantById(Long Id);

	@Transactional
	public void deleteRestaurantById(Long Id);
}
