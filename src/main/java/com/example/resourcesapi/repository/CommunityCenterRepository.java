package com.example.resourcesapi.repository;

import com.example.resourcesapi.model.Community;
import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.model.Resource;
import com.example.resourcesapi.model.TradeResource;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityCenterRepository extends MongoRepository<CommunityCenter, String> {
    @Aggregation(pipeline = {"{\n" +
            "        $project: { name: '$name', currentlyOccupation: '$currentlyOccupation', maxOccupation: '$maxOccupation', highOccupation: { $gt: [{ $divide: [{ $multiply: ['$currentlyOccupation', 100] }, '$maxOccupation'] }, 10] }, }\n" +
            "    }", "{ $match: { 'highOccupation': true } }"})
    List<Community> findHighOccupationCommunityCenters();

    @Aggregation(pipeline = {"    {\n" +
            "        $group: {\n" +
            "            _id: 0, doctor: { $sum: \"$resources.doctor\" }, medKit: { $sum: \"$resources.medKit\" }, voluntary: { $sum: \"$resources.voluntary\" }\n" +
            "            , foodParcel: { $sum: \"$resources.foodParcel\" }, vehicle: { $sum: \"$resources.vehicle\" }\n" +
            "        }\n" +
            "    }"})
    Resource getResourcesCount();

    @CountQuery(value = "{}")
    Integer getCountCommunityCenters();

}
