package com.example.resourcesapi.service;

import com.example.resourcesapi.model.Community;
import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.model.Occupation;
import com.example.resourcesapi.model.Resource;
import com.example.resourcesapi.model.TradeResource;
import com.example.resourcesapi.repository.CommunityCenterRepository;
import com.example.resourcesapi.repository.ResourceTradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommunityCenterService {

    @Autowired
    private CommunityCenterRepository repository;

    @Autowired
    private ResourceTradeRepository tradeRepository;

    public List<Community> getHighOccupationCommunityCenters() {
        return repository.findHighOccupationCommunityCenters();
    }

    public CommunityCenter createCommunityCenter(CommunityCenter communityCenter) {
        return this.repository.save(communityCenter);
    }

    public CommunityCenter updateCommunityCenter(Integer id, CommunityCenter communityCenter) {
        return null;
    }

    public List<CommunityCenter> getCommunityCenters() {
        return this.repository.findAll();
    }

    public CommunityCenter getCommunityCenterById(Integer id) {
        return new CommunityCenter();
    }

    public void deleteCommunityCenter(Integer id) {

    }

    public Occupation updateCommunityCenterOccupation(String id, Occupation occupation) {
        CommunityCenter communityCenter = repository.findById(id).get();
        communityCenter.setCurrentlyOccupation(occupation.getCurrentlyOccupation());
        repository.save(communityCenter);
        return occupation;
    }

    public Occupation getCommunityCenterOccupation(String id) {
        CommunityCenter communityCenter = repository.findById(id).get();
        Occupation occupation = new Occupation(communityCenter.getCurrentlyOccupation());
        return occupation;
    }
    public Resource getCountAllResource() {
        Resource resource = repository.getResourcesCount();
        Integer countAllCommunityCenters = repository.getCountCommunityCenters();
        divideQuantResources(resource, countAllCommunityCenters);
        return resource;
    }

    private void divideQuantResources(Resource resource, Integer divider) {
        resource.setFoodParcel(resource.getFoodParcel()/divider);
        resource.setDoctor(resource.getDoctor()/divider);
        resource.setVehicle(resource.getVehicle()/divider);
        resource.setMedKit(resource.getMedKit()/divider);
        resource.setVoluntary(resource.getVoluntary()/divider);
    }

    public void communityCenterResource(TradeResource tradeResource) throws Exception {
        CommunityCenter firstCommunityCenter = repository.findById(tradeResource.getFirstCenterId()).get();
        CommunityCenter secondCommunityCenter = repository.findById(tradeResource.getSecondCenterId()).get();

        fillNullValues(tradeResource.getFirstCenterResource());
        fillNullValues(tradeResource.getSecondCenterResource());
        validateAvailableTrade(tradeResource.getFirstCenterResource(), firstCommunityCenter.getResources());
        validateAvailableTrade(tradeResource.getSecondCenterResource(), secondCommunityCenter.getResources());
        validateResourceValues(firstCommunityCenter, secondCommunityCenter);
        tradeAndUpdateCommunityCenters(firstCommunityCenter, tradeResource.getFirstCenterResource(), secondCommunityCenter, tradeResource.getSecondCenterResource());
        tradeResource.setCreatedAt(new Date());
        tradeRepository.save(tradeResource);

    }

    private void validateAvailableTrade(Resource requiredResource, Resource communityCenterResource) throws Exception {
        Integer doctorsResult = communityCenterResource.getDoctor() - requiredResource.getDoctor();
        Integer voluntariesResult = communityCenterResource.getVoluntary() - requiredResource.getVoluntary();
        Integer vehiclesResult = communityCenterResource.getVehicle() - requiredResource.getVehicle();
        Integer foodParcelsResult = communityCenterResource.getFoodParcel() - requiredResource.getFoodParcel();
        Integer medKitsResult = communityCenterResource.getMedKit() - requiredResource.getMedKit();
        if (doctorsResult < 0 || voluntariesResult < 0 || vehiclesResult < 0 || foodParcelsResult < 0 || medKitsResult < 0) {
            throw new Exception("Invalid trade");
        }
    }

    private void validateResourceValues(CommunityCenter firstCommunityCenter, CommunityCenter secondCommunityCenter) {
        Integer first = (firstCommunityCenter.getCurrentlyOccupation() * 100) /firstCommunityCenter.getMaxOccupation();
        Integer second = (secondCommunityCenter.getCurrentlyOccupation() * 100) /secondCommunityCenter.getMaxOccupation();
        if(first < 90 && second < 90) {

        }
    }

    private void tradeAndUpdateCommunityCenters(CommunityCenter firstCommunityCenter, Resource firstResource, CommunityCenter secondCommunityCenter, Resource secondResource) {
        tradeCommunityCenter(firstCommunityCenter, firstResource, secondResource);
        tradeCommunityCenter(secondCommunityCenter, secondResource, firstResource);
        repository.save(firstCommunityCenter);
        repository.save(secondCommunityCenter);
    }

    private void tradeCommunityCenter(CommunityCenter communityCenter, Resource requiredResource, Resource provideResource) {
        communityCenter.getResources().setDoctor(communityCenter.getResources().getDoctor() - provideResource.getDoctor());
        communityCenter.getResources().setFoodParcel(communityCenter.getResources().getFoodParcel() - provideResource.getFoodParcel());
        communityCenter.getResources().setMedKit(communityCenter.getResources().getMedKit() - provideResource.getMedKit());
        communityCenter.getResources().setVehicle(communityCenter.getResources().getVehicle() - provideResource.getVehicle());
        communityCenter.getResources().setVoluntary(communityCenter.getResources().getVoluntary() - provideResource.getVoluntary());
        communityCenter.getResources().setDoctor(communityCenter.getResources().getDoctor() + requiredResource.getDoctor());
        communityCenter.getResources().setFoodParcel(communityCenter.getResources().getFoodParcel() + requiredResource.getFoodParcel());
        communityCenter.getResources().setMedKit(communityCenter.getResources().getMedKit() + requiredResource.getMedKit());
        communityCenter.getResources().setVehicle(communityCenter.getResources().getVehicle() + requiredResource.getVehicle());
        communityCenter.getResources().setVoluntary(communityCenter.getResources().getVoluntary() + requiredResource.getVoluntary());
    }

    private void fillNullValues(Resource resource) {
        if(resource.getDoctor() == null) {
            resource.setDoctor(0);
        }
        if(resource.getFoodParcel() == null) {
            resource.setFoodParcel(0);
        }
        if(resource.getMedKit() == null) {
            resource.setMedKit(0);
        }
        if(resource.getVehicle() == null) {
            resource.setVehicle(0);
        }
        if(resource.getVoluntary() == null) {
            resource.setVoluntary(0);
        }

    }

}
