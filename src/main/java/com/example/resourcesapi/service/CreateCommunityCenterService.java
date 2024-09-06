package com.example.resourcesapi.service;

import com.example.resourcesapi.model.CommunityCenter;

@FunctionalInterface
public interface CreateCommunityCenterService {
    CommunityCenter createCommunityCenter(CommunityCenter communityCenter);
}
