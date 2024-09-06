package com.example.resourcesapi.repository;

import com.example.resourcesapi.model.TradeResource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ResourceTradeRepository extends MongoRepository<TradeResource, String> {
    @Query(value = "{$or: [{firstCenterId: ?0}, {secondCenterId: ?0}]}")
    List<TradeResource> findCommunityCenterTradesById(String id);

    @Query(value = "{$or: [{firstCenterId: ?0}, {secondCenterId: ?0}],createdAt: { $lt: ?2,$gt: ?1}}")
    List<TradeResource> findCommunityCenterTradesByIdAndInitialDate(String id, Date initalDate, Date endDate);
}
