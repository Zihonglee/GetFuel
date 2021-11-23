package onetoone.Users;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	public User getUserById(Long id);

//	public User getUserByName(String name);
//	public User findByName(String name);

	@Transactional
	public void deleteUserById(Long id);
}
