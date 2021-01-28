package com.quora.users.repository;

import com.quora.users.entity.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngagementRepository extends JpaRepository<Engagement, String> {
    List<Engagement> findByUserBusinessEmail(String userBusinessEmail);
}
