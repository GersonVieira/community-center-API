package com.example.resourcesapi.service;

import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.repository.CommunityCenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCommunityCenterServiceImp implements GetCommunityCenterService {

    private final CommunityCenterRepository communityCenterRepository;

    @Override
    public CommunityCenter getCommunityCenterById(String id) {
        return communityCenterRepository.findById(id).get();
    }
}
