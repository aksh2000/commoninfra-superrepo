package com.quora.users.service;

import com.quora.users.entity.Engagement;

import java.util.List;

public interface IEngagementService {
    Engagement addEngagement(Engagement engagement);

    List<Engagement> findByUserBusinessEmail(String userBusinessEmail);

    List<Engagement> findBySecondaryEmail(String secondaryEmail);

    List<Engagement> getFollowRequests(String userBusinessEmail);

    Integer acceptFollowRequests(String secondaryEmail, String userBusinessEmail);

    Integer rejectFollowRequests(String secondaryEmail, String userBusinessEmail);

    Integer acceptAllFollowRequests(String userEmail);

    Long getFollowersCount(String userBusinessEmail);

    Long getFollowingCount(String secondaryEmail);
}
