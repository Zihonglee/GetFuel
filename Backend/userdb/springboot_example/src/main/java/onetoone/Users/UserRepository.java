package onetoone.Users;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	public User getUserById(Long id);
	
	@Transactional
	public void deleteUserById(Long id);
}
