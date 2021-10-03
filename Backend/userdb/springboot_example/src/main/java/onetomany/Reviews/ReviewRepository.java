package onetomany.Reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;



public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review findById(int id);

    @Transactional
    void deleteById(int id);


}
