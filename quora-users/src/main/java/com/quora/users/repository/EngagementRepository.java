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
    Integer acceptFollowRequests(String secondaryEmail, String userBusinessEmail);

    @Modifying
    @Transactional
    @Query(value = "delete from quora_engagement where user_business_email = ?2 and secondary_email = ?1", nativeQuery = true)
    Integer rejectFollowRequests(String secondaryEmail, String userBusinessEmail);

    @Modifying
    @Transactional
    @Query(value = "update quora_engagement set is_approved = true where user_business_email = ?1", nativeQuery = true)
    Integer acceptAllFollowRequests(String userEmail);

    @Query(value = "select count(distinct(secondary_email)) from quora_engagement where user_business_email = ?1 and is_approved = true", nativeQuery = true)
    Long getFollowersCount(String userBusinessEmail);

    @Query(value = "select count(distinct(user_business_email)) from quora_engagement where secondary_email = ?1 and is_approved = true", nativeQuery = true)
    Long getFollowingCount(String secondaryEmail);

    Engagement findBySecondaryEmailAndUserBusinessEmail(String secondaryEmail, String userBusinessEmail);
}
