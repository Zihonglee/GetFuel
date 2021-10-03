package onetomany.Cuisine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CuisineRepository extends JpaRepository<Cuisine, Long> {
    Cuisine findById(int id);

    @Transactional
    void deleteById(int id);
}
