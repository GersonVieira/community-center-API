package com.example.resourcesapi.service;

import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.repository.CommunityCenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCommunityCenterServiceImp implements UpdateCommunityCenterService {

    private final CommunityCenterRepository communityCenterRepository;

    @Override
    public CommunityCenter updateCommunityCenter(CommunityCenter communityCenter, String id) {
        communityCenter.setId(id);
        return communityCenterRepository.save(communityCenter);
    }
}
