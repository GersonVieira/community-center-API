package com.example.resourcesapi.controller;

import com.example.resourcesapi.model.Community;
import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.model.Occupation;
import com.example.resourcesapi.model.Resource;
import com.example.resourcesapi.model.TradeResource;
import com.example.resourcesapi.service.CommunityCenterService;
import com.example.resourcesapi.service.CreateCommunityCenterService;
import com.example.resourcesapi.service.DeleteCommunityCenterServiceImp;
import com.example.resourcesapi.service.GetCommunityCenterServiceImp;
import com.example.resourcesapi.service.GetCommunityCenterTradeImp;
import com.example.resourcesapi.service.UpdateCommunityCenterServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/community-centers")
@RequiredArgsConstructor
public class CommunityCenterController {

    private final CommunityCenterService communityCenterService;
    private final CreateCommunityCenterService createCommunityCenterService;
    private final DeleteCommunityCenterServiceImp deleteCommunityCenterServiceImp;
    private final UpdateCommunityCenterServiceImp  updateCommunityCenterServiceImp;
    private final GetCommunityCenterServiceImp getCommunityCenterServiceImp;
    private final GetCommunityCenterTradeImp getCommunityCenterTradeImp;

    @GetMapping
    public ResponseEntity<List<CommunityCenter>> getCommunityCenters() {
        return new ResponseEntity<>(communityCenterService.getCommunityCenters(), HttpStatus.OK);
    }

    @GetMapping(value = "/high-occupation")
    public ResponseEntity<List<Community>> getHighOccupationCommunityCenters() {
        return new ResponseEntity<>(communityCenterService.getHighOccupationCommunityCenters(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommunityCenter> getCommunityCenter(@PathVariable String id) {
        return new ResponseEntity<>(getCommunityCenterServiceImp.getCommunityCenterById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/resources")
    public ResponseEntity<Resource> getCommunityCenter() {
        return new ResponseEntity<>(communityCenterService.getCountAllResource(), HttpStatus.OK);
    }

    @GetMapping(value = "/occupation/{id}")
    public ResponseEntity<Occupation> getCommunityCenterOccupation(@PathVariable String id) {
        return new ResponseEntity<>(communityCenterService.getCommunityCenterOccupation(id), HttpStatus.OK);
    }

    @GetMapping(value = "/trades")
    public ResponseEntity<List<TradeResource>> getCommunityCenterTrades(@RequestParam String communityCenterId,@RequestParam(required = false) Long startDate) throws Exception {
        return new ResponseEntity<>(getCommunityCenterTradeImp.getCommuntyCenterTradesById(communityCenterId, startDate), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommunityCenter> createCommunityCenter(@RequestBody CommunityCenter communityCenter) {
        return new ResponseEntity<>(createCommunityCenterService.createCommunityCenter(communityCenter), HttpStatus.CREATED);
    }

    @PostMapping(value = "/resource-trades")
    public ResponseEntity createCommunityCenterResourceTrades(@RequestBody TradeResource tradeResource) throws Exception {
        communityCenterService.communityCenterResource(tradeResource);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/occupation/{id}")
    public ResponseEntity<Occupation> createCommunityCenter(@PathVariable String id, @RequestBody Occupation occupation) {
        return new ResponseEntity<>(communityCenterService.updateCommunityCenterOccupation(id, occupation), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<CommunityCenter> updateCommunityCenter(@PathVariable String id, @RequestBody CommunityCenter communityCenter) {
        return new ResponseEntity<>(updateCommunityCenterServiceImp.updateCommunityCenter(communityCenter, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCommunityCenter(@PathVariable String id) {
        deleteCommunityCenterServiceImp.deleteCommunityCenter(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
