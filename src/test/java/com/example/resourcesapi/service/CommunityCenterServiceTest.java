package com.example.resourcesapi.service;

import com.example.resourcesapi.model.Community;
import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.model.Occupation;
import com.example.resourcesapi.model.Resource;
import com.example.resourcesapi.repository.CommunityCenterRepository;
import com.example.resourcesapi.repository.ResourceTradeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.resourcesapi.builder.CommunityBuilder.createCommunity;
import static com.example.resourcesapi.builder.CommunityCenterBuilder.createCommunityCenter;
import static com.example.resourcesapi.builder.ResourceBuilder.createResource;
import static com.example.resourcesapi.builder.TradeResourceBuilder.createTradeResource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("Service")
@ExtendWith(MockitoExtension.class)
@DisplayName("Validates the functionalities of CommunityCenterService")
class CommunityCenterServiceTest {

    @Mock
    private CommunityCenterRepository repository;

    @Mock
    private ResourceTradeRepository tradeRepository;

    @InjectMocks
    private CommunityCenterService communityCenterService;

    @Test
    @DisplayName("Get High Occupation Community Centers")
    void shouldGetHighOccupationCommunityCenters() {

        when(repository.findHighOccupationCommunityCenters()).thenReturn(List.of(createCommunity()));

        List<Community> highOccupationCommunityCenters = communityCenterService.getHighOccupationCommunityCenters();

        verify(repository).findHighOccupationCommunityCenters();

        assertAll("verify Community fields",
                () -> assertThat(highOccupationCommunityCenters.get(0).getId(), is("uuid")),
                () -> assertThat(highOccupationCommunityCenters.get(0).getName(), is("Name Community")),
                () -> assertThat(highOccupationCommunityCenters.get(0).getAddress(), is("Address Community")),
                () -> assertThat(highOccupationCommunityCenters.get(0).getMaxOccupation(), is(1)),
                () -> assertThat(highOccupationCommunityCenters.get(0).getCurrentlyOccupation(), is(2)),
                () -> assertThat(highOccupationCommunityCenters.get(0).getHighOccupation(), is(true))
        );
    }

    @Test
    @DisplayName("Create Community Center")
    void shouldCreateCommunityCenter() {

        when(repository.save(any())).thenReturn(createCommunityCenter());

        CommunityCenter communityCenter = communityCenterService.createCommunityCenter(createCommunityCenter());

        ArgumentCaptor<CommunityCenter> argumentCaptor = ArgumentCaptor.forClass(CommunityCenter.class);
        verify(repository).save(argumentCaptor.capture());
        CommunityCenter communityCenterSend = argumentCaptor.getValue();

        assertAll("verify CommunityCenter fields",
                () -> assertThat(communityCenter.getId(), is("uuid")),
                () -> assertThat(communityCenter.getName(), is("Name Community")),
                () -> assertThat(communityCenter.getLocation(), is("Location Community")),
                () -> assertThat(communityCenter.getAddress(), is("Address Community")),
                () -> assertThat(communityCenter.getMaxOccupation(), is(2)),
                () -> assertThat(communityCenter.getCurrentlyOccupation(), is(1)),
                () -> assertThat(communityCenter.getResources().getId(), is("uuid2")),
                () -> assertThat(communityCenter.getResources().getDoctor(), is(1)),
                () -> assertThat(communityCenter.getResources().getMedKit(), is(2)),
                () -> assertThat(communityCenter.getResources().getVoluntary(), is(3)),
                () -> assertThat(communityCenter.getResources().getFoodParcel(), is(4)),
                () -> assertThat(communityCenter.getResources().getVehicle(), is(6))
        );

        assertAll("verify communityCenterSend fields",
                () -> assertThat(communityCenterSend.getId(), is("uuid")),
                () -> assertThat(communityCenterSend.getName(), is("Name Community")),
                () -> assertThat(communityCenterSend.getLocation(), is("Location Community")),
                () -> assertThat(communityCenterSend.getAddress(), is("Address Community")),
                () -> assertThat(communityCenterSend.getMaxOccupation(), is(2)),
                () -> assertThat(communityCenterSend.getCurrentlyOccupation(), is(1)),
                () -> assertThat(communityCenterSend.getResources().getId(), is("uuid2")),
                () -> assertThat(communityCenterSend.getResources().getDoctor(), is(1)),
                () -> assertThat(communityCenterSend.getResources().getMedKit(), is(2)),
                () -> assertThat(communityCenterSend.getResources().getVoluntary(), is(3)),
                () -> assertThat(communityCenterSend.getResources().getFoodParcel(), is(4)),
                () -> assertThat(communityCenterSend.getResources().getVehicle(), is(6))
        );
    }

    @Test
    @DisplayName("Get Community Centers")
    void shouldGetCommunityCenters() {

        when(repository.findAll()).thenReturn(List.of(createCommunityCenter()));

        List<CommunityCenter> communityCenters = communityCenterService.getCommunityCenters();

        verify(repository).findAll();

        assertAll("verify communityCenters fields",
                () -> assertThat(communityCenters.get(0).getId(), is("uuid")),
                () -> assertThat(communityCenters.get(0).getName(), is("Name Community")),
                () -> assertThat(communityCenters.get(0).getLocation(), is("Location Community")),
                () -> assertThat(communityCenters.get(0).getAddress(), is("Address Community")),
                () -> assertThat(communityCenters.get(0).getMaxOccupation(), is(2)),
                () -> assertThat(communityCenters.get(0).getCurrentlyOccupation(), is(1)),
                () -> assertThat(communityCenters.get(0).getResources().getId(), is("uuid2")),
                () -> assertThat(communityCenters.get(0).getResources().getDoctor(), is(1)),
                () -> assertThat(communityCenters.get(0).getResources().getMedKit(), is(2)),
                () -> assertThat(communityCenters.get(0).getResources().getVoluntary(), is(3)),
                () -> assertThat(communityCenters.get(0).getResources().getFoodParcel(), is(4)),
                () -> assertThat(communityCenters.get(0).getResources().getVehicle(), is(6))
        );
    }

    @Test
    @DisplayName("Update Community Center Occupation")
    void shouldUpdateCommunityCenterOccupation() {

        when(repository.findById(anyString())).thenReturn(Optional.of(createCommunityCenter()));

        Occupation occupation = communityCenterService.updateCommunityCenterOccupation("uuid", new Occupation(1));

        verify(repository).findById(eq("uuid"));
        verify(repository).save(any());

        assertAll("verify Occupation fields",
                () -> assertThat(occupation.getCurrentlyOccupation(), is(1))
        );
    }

    @Test
    @DisplayName("Get Community Center Occupation")
    void shouldGetCommunityCenterOccupation() {

        when(repository.findById(anyString())).thenReturn(Optional.of(createCommunityCenter()));

        Occupation occupation = communityCenterService.getCommunityCenterOccupation("uuid");

        verify(repository).findById(eq("uuid"));

        assertAll("verify Occupation fields",
                () -> assertThat(occupation.getCurrentlyOccupation(), is(1))
        );
    }

    @Test
    @DisplayName("Get Count All Resource")
    void shouldGetCountAllResource() {

        when(repository.getResourcesCount()).thenReturn(createResource());
        when(repository.getCountCommunityCenters()).thenReturn(1);

        Resource countAllResource = communityCenterService.getCountAllResource();

        verify(repository).getResourcesCount();
        verify(repository).getCountCommunityCenters();

        assertAll("verify Occupation fields",
                () -> assertThat(countAllResource.getId(), is("uuid2")),
                () -> assertThat(countAllResource.getDoctor(), is(1)),
                () -> assertThat(countAllResource.getMedKit(), is(2)),
                () -> assertThat(countAllResource.getVoluntary(), is(3)),
                () -> assertThat(countAllResource.getFoodParcel(), is(4)),
                () -> assertThat(countAllResource.getVehicle(), is(6))
        );
    }

    @Test
    @DisplayName("Get Count All Resource")
    void shouldCreateCommunityCenterResource() throws Exception {

        when(repository.findById(anyString())).thenReturn(Optional.of(createCommunityCenter()));
        when(repository.findById(anyString())).thenReturn(Optional.of(createCommunityCenter()));

        communityCenterService.communityCenterResource(createTradeResource());

        verify(repository, times(2)).findById(anyString());
        verify(repository, times(2)).save(any());
        verify(tradeRepository).save(any());
    }

}