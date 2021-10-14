<<<<<<< HEAD
package com.example.demoAPI;
=======
package onetoone.Users;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2

<<<<<<< HEAD
=======
import javax.transaction.Transactional;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

>>>>>>> d6fff4d9101be906ed18cbf242c239fcacc7d5fd
@Repository
public interface personRepository extends JpaRepository<Person, Long>
{
	public Person findPersonById(Long id);
=======
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	public User findPersonById(Long id);
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2
	
	@Transactional
	public void deleteById(String id);
}
