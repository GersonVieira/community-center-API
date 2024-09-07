package com.example.resourcesapi.controller;

import com.example.resourcesapi.model.Community;
import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.model.Occupation;
import com.example.resourcesapi.model.Resource;
import com.example.resourcesapi.model.TradeResource;
import com.example.resourcesapi.service.CommunityCenterService;
import com.example.resourcesapi.service.CreateCommunityCenterServiceImpl;
import com.example.resourcesapi.service.DeleteCommunityCenterServiceImp;
import com.example.resourcesapi.service.GetCommunityCenterServiceImp;
import com.example.resourcesapi.service.GetCommunityCenterTradeImp;
import com.example.resourcesapi.service.UpdateCommunityCenterServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/community-centers")
@RequiredArgsConstructor
@Tag(name = "Community Center Controller", description = "Endpoints Related to CommunityCenter Management")
public class CommunityCenterController {

    private final CommunityCenterService communityCenterService;
    private final CreateCommunityCenterServiceImpl createCommunityCenterService;
    private final DeleteCommunityCenterServiceImp deleteCommunityCenterServiceImp;
    private final UpdateCommunityCenterServiceImp  updateCommunityCenterServiceImp;
    private final GetCommunityCenterServiceImp getCommunityCenterServiceImp;
    private final GetCommunityCenterTradeImp getCommunityCenterTradeImp;

    @GetMapping
    @Operation(summary = "Get Community Centers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CommunityCenter Returned Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    public ResponseEntity<List<CommunityCenter>> getCommunityCenters() {
        return new ResponseEntity<>(communityCenterService.getCommunityCenters(), HttpStatus.OK);
    }

    @Operation(summary = "Get High Occupation Community Centers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "High Occupation Community Returned Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    @GetMapping(value = "/high-occupation")
    public ResponseEntity<List<Community>> getHighOccupationCommunityCenters() {
        return new ResponseEntity<>(communityCenterService.getHighOccupationCommunityCenters(), HttpStatus.OK);
    }

    @Operation(summary = "Get Community Centers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community Returned Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<CommunityCenter> getCommunityCenter(@PathVariable String id) {
        return new ResponseEntity<>(getCommunityCenterServiceImp.getCommunityCenterById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get Community Resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community Resource Returned Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    @GetMapping(value = "/resources")
    public ResponseEntity<Resource> getCommunityResource() {
        return new ResponseEntity<>(communityCenterService.getCountAllResource(), HttpStatus.OK);
    }

    @Operation(summary = "Get Community Center Occupation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community Occupation Returned Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    @GetMapping(value = "/occupation/{id}")
    public ResponseEntity<Occupation> getCommunityCenterOccupation(@PathVariable String id) {
        return new ResponseEntity<>(communityCenterService.getCommunityCenterOccupation(id), HttpStatus.OK);
    }

    @Operation(summary = "Get Community Center Trades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community Resource Returned Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    @GetMapping(value = "/trades")
    public ResponseEntity<List<TradeResource>> getCommunityCenterTrades(@RequestParam String communityCenterId,@RequestParam(required = false) Long startDate) throws Exception {
        return new ResponseEntity<>(getCommunityCenterTradeImp.getCommuntyCenterTradesById(communityCenterId, startDate), HttpStatus.OK);
    }

    @Operation(summary = "Create Community Center")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community Center Returned Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    @PostMapping
    public ResponseEntity<CommunityCenter> createCommunityCenter(@RequestBody CommunityCenter communityCenter) {
        return new ResponseEntity<>(createCommunityCenterService.createCommunityCenter(communityCenter), HttpStatus.CREATED);
    }

    @Operation(summary = "Create Community Center Resource Trades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community Center Created Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    @PostMapping(value = "/resource-trades")
    public ResponseEntity<Void> createCommunityCenterResourceTrades(@RequestBody TradeResource tradeResource) throws Exception {
        communityCenterService.communityCenterResource(tradeResource);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Create Community Center")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community Center Created Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    @PatchMapping(value = "/occupation/{id}")
    public ResponseEntity<Occupation> createCommunityCenter(@PathVariable String id, @RequestBody Occupation occupation) {
        return new ResponseEntity<>(communityCenterService.updateCommunityCenterOccupation(id, occupation), HttpStatus.OK);
    }

    @Operation(summary = "Update Community Center")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community Center Updated Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    @PatchMapping(path = "/{id}")
    public ResponseEntity<CommunityCenter> updateCommunityCenter(@PathVariable String id, @RequestBody CommunityCenter communityCenter) {
        return new ResponseEntity<>(updateCommunityCenterServiceImp.updateCommunityCenter(communityCenter, id), HttpStatus.OK);
    }

    @Operation(summary = "Delete Community Center")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Community Resource Returned Successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCommunityCenter(@PathVariable String id) {
        deleteCommunityCenterServiceImp.deleteCommunityCenter(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
