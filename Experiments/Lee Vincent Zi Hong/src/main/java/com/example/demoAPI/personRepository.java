package com.example.demoAPI;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoMODEL.Person;

public interface personRepository extends JpaRepository<Person, UUID>
{
	public Person findPersonById(UUID id);
	
	@Transactional
	public void deleteById(UUID id);
}
