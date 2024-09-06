package com.example.resourcesapi.service;

import com.example.resourcesapi.model.CommunityCenter;

@FunctionalInterface
public interface UpdateCommunityCenterService {
    CommunityCenter updateCommunityCenter(CommunityCenter communityCenter, String id);
}
