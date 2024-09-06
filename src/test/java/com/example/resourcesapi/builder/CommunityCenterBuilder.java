package com.example.resourcesapi.builder;

import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.model.Resource;

public class CommunityCenterBuilder {

    public static CommunityCenter createCommunityCenter() {
        return CommunityCenter.builder()
                .id("uuid")
                .name("Name Community")
                .location("Location Community")
                .address("Address Community")
                .maxOccupation(2)
                .currentlyOccupation(1)
                .resources(Resource.builder()
                        .id("uuid2")
                        .doctor(1)
                        .medKit(2)
                        .voluntary(3)
                        .foodParcel(4)
                        .vehicle(6)
                        .build())
                .build();
    }
}
