package com.example.resourcesapi.service;

import com.example.resourcesapi.model.TradeResource;
import com.example.resourcesapi.repository.CommunityCenterRepository;
import com.example.resourcesapi.repository.ResourceTradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCommunityCenterTradeImp implements GetCommunityCenterTrade{

    private final ResourceTradeRepository resourceTradeRepository;

    @Override
    public List<TradeResource> getCommuntyCenterTradesById(String id, Long initialDate) throws Exception {
        if(id == null) {
            throw new Exception("Required params community center id");
        }
        if (initialDate == null) {
            return resourceTradeRepository.findCommunityCenterTradesById(id);
        }
        return resourceTradeRepository.findCommunityCenterTradesByIdAndInitialDate(id, new Date(initialDate), new Date());
    }
}
