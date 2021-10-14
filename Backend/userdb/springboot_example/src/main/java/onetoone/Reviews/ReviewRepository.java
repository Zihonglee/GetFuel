package com.example.ReviewsAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
=======

>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> 
{
    public Review findReviewById(Long id);

    @Transactional
    public void deleteReviewById(Long id);
}
