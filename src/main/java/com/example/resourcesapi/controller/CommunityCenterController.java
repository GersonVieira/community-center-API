package com.example.resourcesapi.controller;

import com.example.resourcesapi.model.Community;
import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.model.Occupation;
import com.example.resourcesapi.model.Resource;
import com.example.resourcesapi.model.TradeResource;
import com.example.resourcesapi.service.CommunityCenterService;
import com.example.resourcesapi.service.CreateCommunityCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/community-centers")
@RequiredArgsConstructor
public class CommunityCenterController {

    private final CommunityCenterService communityCenterService;
    private final CreateCommunityCenterService createCommunityCenterService;

    @GetMapping
    public ResponseEntity<List<CommunityCenter>> getCommunityCenters() {
        return new ResponseEntity<>(communityCenterService.getCommunityCenters(), HttpStatus.OK);
    }

    @GetMapping(value = "/high-occupation")
    public ResponseEntity<List<Community>> getHighOccupationCommunityCenters() {
        return new ResponseEntity<>(communityCenterService.getHighOccupationCommunityCenters(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommunityCenter> getCommunityCenter(@PathVariable Integer id) {
        return new ResponseEntity<>(communityCenterService.getCommunityCenterById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/resources")
    public ResponseEntity<Resource> getCommunityCenter() {
        return new ResponseEntity<>(communityCenterService.getCountAllResource(), HttpStatus.OK);
    }

    @GetMapping(value = "/occupation/{id}")
    public ResponseEntity<Occupation> getCommunityCenterOccupation(@PathVariable String id) {
        return new ResponseEntity<>(communityCenterService.getCommunityCenterOccupation(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommunityCenter> createCommunityCenter(@RequestBody CommunityCenter communityCenter) {
        return new ResponseEntity<>(communityCenterService.createCommunityCenter(communityCenter), HttpStatus.CREATED);
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
    public ResponseEntity<CommunityCenter> updateCommunityCenter(@PathVariable Integer id, @RequestBody CommunityCenter communityCenter) {
        return new ResponseEntity<>(communityCenterService.updateCommunityCenter(id, communityCenter), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCommunityCenter(@PathVariable Integer id) {
        communityCenterService.deleteCommunityCenter(id);
        return new ResponseEntity<>(new String[]{"Tarô do jongas", "Zezin el buezo"}, HttpStatus.OK);
    }

    @GetMapping(value = "/error")
    public ResponseEntity getMoviesError() {
        return new ResponseEntity<>("Error test", HttpStatus.BAD_REQUEST);
    }
}
