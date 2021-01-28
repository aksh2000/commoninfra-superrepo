package com.quora.users.repository;

import com.quora.users.entity.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EngagementRepository extends JpaRepository<Engagement, String> {
    List<Engagement> findByUserBusinessEmail(String userBusinessEmail);

    List<Engagement> findBySecondaryEmail(String secondaryEmail);

    @Query(value = "select * from quora_engagement where user_business_email = ?1 and is_approved = false",nativeQuery = true)
    List<Engagement> getFollowRequests(String userBusinessEmail);

    @Modifying
    @Transactional
    @Query(value = "update quora_engagement set is_approved = true where user_business_email = ?2 and secondary_email = ?1", nativeQuery = true)
    Long acceptFollowRequests(String secondaryEmail, String userBusinessEmail);

    @Modifying
    @Transactional
    @Query(value = "delete from quora_engagement where user_business_email = ?2 and secondary_email = ?1", nativeQuery = true)
    Long rejectFollowRequests(String secondaryEmail, String userBusinessEmail);

    @Modifying
    @Transactional
    @Query(value = "update quora_engagement set is_approved = true where user_business_email = ?1", nativeQuery = true)
    Long acceptAllFollowRequests(String userEmail);
}
