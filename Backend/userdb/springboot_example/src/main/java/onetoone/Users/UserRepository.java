package com.example.demoAPI;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface personRepository extends JpaRepository<Person, Long>
{
	public Person findPersonById(Long id);
	
	@Transactional
	public void deleteById(String id);
}
