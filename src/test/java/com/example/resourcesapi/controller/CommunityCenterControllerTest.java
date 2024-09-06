package com.example.resourcesapi.controller;

import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.model.Occupation;
import com.example.resourcesapi.model.TradeResource;
import com.example.resourcesapi.service.CommunityCenterService;
import com.example.resourcesapi.service.CreateCommunityCenterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static com.example.resourcesapi.builder.CommunityBuilder.createCommunity;
import static com.example.resourcesapi.builder.CommunityCenterBuilder.createCommunityCenter;
import static com.example.resourcesapi.builder.TradeResourceBuilder.createTradeResource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(CommunityCenterController.class)
@DisplayName("Validates the functionalities of CommunityCenterController")
class CommunityCenterControllerTest {

    @MockBean
    private CommunityCenterService communityCenterService;
    @MockBean
    private CreateCommunityCenterService createCommunityCenterService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final String urlBase = "/community-centers";
    private final String urlHighOccupation = urlBase + "/high-occupation";
    private final String urlCommunityCenter = urlBase + "{id}";
    private final String urlRessources = urlBase + "/resources";
    private final String urlOccupationId = urlBase + "/occupation/{id}";
    private final String urlResourceTrades = urlBase + "/resource-trades";
    private final String urlOccupation = urlBase + "/occupation/{id}";

    @Test
    @DisplayName("Get Community Centers")
    void shouldGetCommunityCenters() throws Exception {
        when(communityCenterService.getCommunityCenters()).thenReturn(List.of(createCommunityCenter()));

        mockMvc.perform(get(urlBase)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType("application/json")
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].id").value("uuid"),
                        jsonPath("$[0].name").value("Name Community"),
                        jsonPath("$[0].location").value("Location Community"),
                        jsonPath("$[0].address").value("Address Community"),
                        jsonPath("$[0].maxOccupation").value(2),
                        jsonPath("$[0].currentlyOccupation").value(1),
                        jsonPath("$[0].resources.id").value("uuid2"),
                        jsonPath("$[0].resources.doctor").value(1),
                        jsonPath("$[0].resources.medKit").value(2),
                        jsonPath("$[0].resources.voluntary").value(3),
                        jsonPath("$[0].resources.foodParcel").value(4),
                        jsonPath("$[0].resources.vehicle").value(6)
                );

        verify(communityCenterService).getCommunityCenters();
    }

    @Test
    @DisplayName("Get High Occupation Community Centers")
    void shouldGetHighOccupationCommunityCenters() throws Exception {
        when(communityCenterService.getHighOccupationCommunityCenters()).thenReturn(List.of(createCommunity()));

        mockMvc.perform(get(urlHighOccupation)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType("application/json")
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[0].id").value("uuid"),
                        jsonPath("$[0].name").value("Name Community"),
                        jsonPath("$[0].address").value("Address Community"),
                        jsonPath("$[0].maxOccupation").value(1),
                        jsonPath("$[0].currentlyOccupation").value(2),
                        jsonPath("$[0].highOccupation").value(true)
                );

        verify(communityCenterService).getHighOccupationCommunityCenters();
    }

    /*@Test
    @DisplayName("Get Community Center")
    void shouldGetCommunityCenter() throws Exception {
        when(communityCenterService.getCommunityCenterById(anyString())).thenReturn(createCommunity());

        mockMvc.perform(get(urlCommunityCenter, "uuid")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType("application/json")
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value("uuid"),
                        jsonPath("$.name").value("Name Community"),
                        jsonPath("$.location").value("Location Community"),
                        jsonPath("$.address").value("Address Community"),
                        jsonPath("$.maxOccupation").value(2),
                        jsonPath("$.currentlyOccupation").value(1),
                        jsonPath("$.resources.id").value("uuid2"),
                        jsonPath("$.resources.doctor").value(1),
                        jsonPath("$.resources.medKit").value(2),
                        jsonPath("$.resources.voluntary").value(3),
                        jsonPath("$.resources.foodParcel").value(4),
                        jsonPath("$.resources.vehicle").value(6)
                );

        verify(communityCenterService).getCommunityCenterById(anyString());
    }*/

    @Test
    @DisplayName("Get Resources")
    void shouldGetResources() throws Exception {
        when(communityCenterService.getCountAllResource()).thenReturn(createCommunityCenter().getResources());

        mockMvc.perform(get(urlRessources)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType("application/json")
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value("uuid2"),
                        jsonPath("$.doctor").value(1),
                        jsonPath("$.medKit").value(2),
                        jsonPath("$.voluntary").value(3),
                        jsonPath("$.foodParcel").value(4),
                        jsonPath("$.vehicle").value(6)
                );

        verify(communityCenterService).getCountAllResource();
    }

    @Test
    @DisplayName("Get Community Center Occupation")
    void shouldGetCommunityCenterOccupation() throws Exception {
        when(communityCenterService.getCommunityCenterOccupation(anyString())).thenReturn(new Occupation(1));

        mockMvc.perform(get(urlOccupationId, "uuid")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType("application/json")
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.currentlyOccupation").value(1)
                );

        verify(communityCenterService).getCommunityCenterOccupation(anyString());
    }

    @Test
    @DisplayName("Create Community Center")
    void shouldCreateCommunityCenter() throws Exception {
        when(communityCenterService.createCommunityCenter(any(CommunityCenter.class))).thenReturn(createCommunityCenter());

        mockMvc.perform(post(urlBase)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createCommunityCenter()))
                )
                .andExpectAll(
                        status().isCreated(),
                        jsonPath("$.id").value("uuid"),
                        jsonPath("$.name").value("Name Community"),
                        jsonPath("$.location").value("Location Community"),
                        jsonPath("$.address").value("Address Community"),
                        jsonPath("$.maxOccupation").value(2),
                        jsonPath("$.currentlyOccupation").value(1),
                        jsonPath("$.resources.id").value("uuid2"),
                        jsonPath("$.resources.doctor").value(1),
                        jsonPath("$.resources.medKit").value(2),
                        jsonPath("$.resources.voluntary").value(3),
                        jsonPath("$.resources.foodParcel").value(4),
                        jsonPath("$.resources.vehicle").value(6)
                );

        verify(communityCenterService).createCommunityCenter(any(CommunityCenter.class));
    }

    @Test
    @DisplayName("Create Community Center Resource Trades")
    void shouldCreateCommunityCenterResourceTrades() throws Exception {
        doNothing().when(communityCenterService).communityCenterResource(any(TradeResource.class));

        mockMvc.perform(post(urlResourceTrades)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createTradeResource()))
                )
                .andExpectAll(
                        status().isOk()
                );

        verify(communityCenterService).communityCenterResource(any(TradeResource.class));
    }

    @Test
    @DisplayName("Create Community Occupation")
    void shouldCreateCommunityOccupation() throws Exception {
        when(communityCenterService.updateCommunityCenterOccupation(anyString(), any())).thenReturn(new Occupation(1));

        mockMvc.perform(patch(urlOccupation, "uuid")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new Occupation(1)))
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.currentlyOccupation").value(1)
                );

        verify(communityCenterService).updateCommunityCenterOccupation(anyString(), any());
    }

    @Test
    @DisplayName("Update Community Center")
    void shouldUpdateCommunityCenter() throws Exception {
        when(communityCenterService.updateCommunityCenter(anyInt(), any())).thenReturn(createCommunityCenter());

        mockMvc.perform(patch(urlBase + "/{id}", 1)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createCommunityCenter()))
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value("uuid"),
                        jsonPath("$.name").value("Name Community"),
                        jsonPath("$.location").value("Location Community"),
                        jsonPath("$.address").value("Address Community"),
                        jsonPath("$.maxOccupation").value(2),
                        jsonPath("$.currentlyOccupation").value(1),
                        jsonPath("$.resources.id").value("uuid2"),
                        jsonPath("$.resources.doctor").value(1),
                        jsonPath("$.resources.medKit").value(2),
                        jsonPath("$.resources.voluntary").value(3),
                        jsonPath("$.resources.foodParcel").value(4),
                        jsonPath("$.resources.vehicle").value(6)
                );

        verify(communityCenterService).updateCommunityCenter(anyInt(), any());
    }

    /*@Test
    @DisplayName("Delete Community Center")
    void shouldDeleteCommunityCenter() throws Exception {
        when(communityCenterService.updateCommunityCenter(anyInt(), any())).thenReturn(createCommunityCenter());

        mockMvc.perform(patch(urlBase + "/{id}", 1)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createCommunityCenter()))
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id").value("uuid"),
                        jsonPath("$.name").value("Name Community"),
                        jsonPath("$.location").value("Location Community"),
                        jsonPath("$.address").value("Address Community"),
                        jsonPath("$.maxOccupation").value(2),
                        jsonPath("$.currentlyOccupation").value(1),
                        jsonPath("$.resources.id").value("uuid2"),
                        jsonPath("$.resources.doctor").value(1),
                        jsonPath("$.resources.medKit").value(2),
                        jsonPath("$.resources.voluntary").value(3),
                        jsonPath("$.resources.foodParcel").value(4),
                        jsonPath("$.resources.vehicle").value(6)
                );

        verify(communityCenterService).updateCommunityCenter(anyInt(), any());
    }*/
}