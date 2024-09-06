package com.example.resourcesapi.service;

import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.repository.CommunityCenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommunityCenterServiceImpl implements CreateCommunityCenterService {

    private final CommunityCenterRepository communityCenterRepository;

    @Override
    public CommunityCenter createCommunityCenter(CommunityCenter communityCenter) {
        return communityCenterRepository.save(new CommunityCenter());
    }
}
