package onetoone.Users;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	public User findPersonById(Long id);
	
	@Transactional
	public void deleteById(String id);
}
