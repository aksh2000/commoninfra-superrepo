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
    public Long acceptFollowRequests(String secondaryEmail, String userBusinessEmail) {
        return engagementRepository.acceptFollowRequests(secondaryEmail,userBusinessEmail);
    }

    @Override
    public Long rejectFollowRequests(String secondaryEmail, String userBusinessEmail) {
        return engagementRepository.rejectFollowRequests(secondaryEmail, userBusinessEmail);
    }

    @Override
    public Long acceptAllFollowRequests(String userEmail) {
        return engagementRepository.acceptAllFollowRequests(userEmail);
    }
}
