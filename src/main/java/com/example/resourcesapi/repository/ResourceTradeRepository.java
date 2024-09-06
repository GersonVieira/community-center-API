package com.example.resourcesapi.repository;

import com.example.resourcesapi.model.TradeResource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceTradeRepository extends MongoRepository<TradeResource, String> {
}
