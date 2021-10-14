package onetoone.Reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> 
{
    public Review findReviewById(Long id);

    @Transactional
    public void deleteReviewById(Long id);
}
