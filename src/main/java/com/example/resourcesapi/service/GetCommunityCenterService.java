package com.example.resourcesapi.service;

import com.example.resourcesapi.model.CommunityCenter;

@FunctionalInterface
public interface GetCommunityCenterService {
    CommunityCenter getCommunityCenterById(String id);
}
