package com.example.resourcesapi.builder;

import com.example.resourcesapi.model.TradeResource;

import java.util.Date;

import static com.example.resourcesapi.builder.CommunityCenterBuilder.createCommunityCenter;

public class TradeResourceBuilder {

    public static TradeResource createTradeResource() {
        return TradeResource.builder()
                .firstCenterId("firstCenterId")
                .secondCenterId("secondCenterId")
                .firstCenterResource(createCommunityCenter().getResources())
                .secondCenterResource(createCommunityCenter().getResources())
                .createdAt(new Date())
                .build();
    }
}
