package com.example.resourcesapi.builder;

import com.example.resourcesapi.model.Community;

public class CommunityBuilder {

    public static Community createCommunity() {
        return Community.builder()
                .id("uuid")
                .name("Name Community")
                .address("Address Community")
                .maxOccupation(1)
                .currentlyOccupation(2)
                .highOccupation(true)
                .build();
    }
}
