package onetoone.Cuisine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> 
{
    public Cuisine findCuisineById(Long id);

    @Transactional
    public void deleteCuisineById(Long id);
}
