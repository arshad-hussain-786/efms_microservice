package com.efms.recruitcrm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efms.recruitcrm.entities.PerformanceReview;

@Repository
public interface PerformaceReviewRepository extends JpaRepository<PerformanceReview, Long> {

}
