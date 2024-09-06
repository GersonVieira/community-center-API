package com.example.resourcesapi.service;


import com.example.resourcesapi.model.TradeResource;

import java.util.List;

@FunctionalInterface
public interface GetCommunityCenterTrade {
    List<TradeResource> getCommuntyCenterTradesById(String id, Long initialDate) throws Exception;
}
