package com.quora.users.service;

import com.quora.users.entity.Engagement;

import java.util.List;

public interface IEngagementService {
    Engagement addEngagement(Engagement engagement);

    List<Engagement> findByUserBusinessEmail(String userBusinessEmail);
}
