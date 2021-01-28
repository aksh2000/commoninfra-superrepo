package com.quora.users.service.impl;

import com.quora.users.entity.Engagement;
import com.quora.users.repository.EngagementRepository;
import com.quora.users.service.IEngagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngagementServiceImpl implements IEngagementService {
    @Autowired
    EngagementRepository engagementRepository;
    @Override
    public Engagement addEngagement(Engagement engagement) {
        return engagementRepository.save(engagement);
    }
}
