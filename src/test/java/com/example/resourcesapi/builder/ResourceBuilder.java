package com.example.resourcesapi.builder;

import com.example.resourcesapi.model.Resource;

public class ResourceBuilder {

    public static Resource createResource() {
        return Resource.builder()
                .id("uuid2")
                .doctor(1)
                .medKit(2)
                .voluntary(3)
                .foodParcel(4)
                .vehicle(6)
                .build();
    }
}
