package com.quora.users.service;

import com.quora.users.entity.Engagement;

import java.util.List;

public interface IEngagementService {
    Engagement addEngagement(Engagement engagement);

    List<Engagement> findByUserBusinessEmail(String userBusinessEmail);

    List<Engagement> findBySecondaryEmail(String secondaryEmail);

    List<Engagement> getFollowRequests(String userBusinessEmail);

    Long acceptFollowRequests(String secondaryEmail, String userBusinessEmail);

    Long rejectFollowRequests(String secondaryEmail, String userBusinessEmail);

    Long acceptAllFollowRequests(String userEmail);

    Long getFollowersCount(String userBusinessEmail);

    Long getFollowingCount(String secondaryEmail);
}
