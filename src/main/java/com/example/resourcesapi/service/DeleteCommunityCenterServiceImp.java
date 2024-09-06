package com.example.resourcesapi.service;

import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.repository.CommunityCenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommunityCenterServiceImp implements DeleteCommunityCenterService {

    private final CommunityCenterRepository communityCenterRepository;
    @Override
    public void deleteCommunityCenter(String id) {
        communityCenterRepository.deleteById(id);
    }
}
