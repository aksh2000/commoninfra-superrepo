package com.quora.users.service.impl;

import com.quora.users.entity.Engagement;
import com.quora.users.repository.EngagementRepository;
import com.quora.users.service.IEngagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngagementServiceImpl implements IEngagementService {
    @Autowired
    EngagementRepository engagementRepository;
    @Override
    public Engagement addEngagement(Engagement engagement) {
        return engagementRepository.save(engagement);
    }

    @Override
    public List<Engagement> findByUserBusinessEmail(String userBusinessEmail) {
        return engagementRepository.findByUserBusinessEmail(userBusinessEmail);
    }

    @Override
    public List<Engagement> findBySecondaryEmail(String secondaryEmail) {
        return engagementRepository.findBySecondaryEmail(secondaryEmail);
    }

    @Override
    public List<Engagement> getFollowRequests(String userBusinessEmail) {
        return engagementRepository.getFollowRequests(userBusinessEmail);
    }

    @Override
    public Integer acceptFollowRequests(String secondaryEmail, String userBusinessEmail) {
        return engagementRepository.acceptFollowRequests(secondaryEmail,userBusinessEmail);
    }

    @Override
    public Integer rejectFollowRequests(String secondaryEmail, String userBusinessEmail) {
        return engagementRepository.rejectFollowRequests(secondaryEmail, userBusinessEmail);
    }

    @Override
    public Integer acceptAllFollowRequests(String userEmail) {
        return engagementRepository.acceptAllFollowRequests(userEmail);
    }

    @Override
    public Long getFollowersCount(String userBusinessEmail) {
        return engagementRepository.getFollowersCount(userBusinessEmail);
    }

    @Override
    public Long getFollowingCount(String secondaryEmail) {
        return engagementRepository.getFollowingCount(secondaryEmail);
    }

    @Override
    public Engagement getFollowStatus(String secondaryEmail, String userBusinessEmail) {
        return engagementRepository.findBySecondaryEmailAndUserBusinessEmail(secondaryEmail, userBusinessEmail);
    }
}
