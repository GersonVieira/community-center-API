package com.example.resourcesapi.service;

import com.example.resourcesapi.model.CommunityCenter;
import com.example.resourcesapi.repository.CommunityCenterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.resourcesapi.builder.CommunityCenterBuilder.createCommunityCenter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("Service")
@ExtendWith(MockitoExtension.class)
@DisplayName("Validates the functionalities of CreateCommunityCenterServiceImpl")
class CreateCommunityCenterServiceImplTest {

    @Mock
    private CommunityCenterRepository communityCenterRepository;

    @InjectMocks
    private CreateCommunityCenterServiceImpl communityCenterService;

    @Test
    @DisplayName("Create Community Center")
    void shouldCreateCommunityCenter() {

        when(communityCenterRepository.save(any())).thenReturn(createCommunityCenter());

        CommunityCenter communityCenter = communityCenterService.createCommunityCenter(createCommunityCenter());

        ArgumentCaptor<CommunityCenter> argumentCaptor = ArgumentCaptor.forClass(CommunityCenter.class);
        verify(communityCenterRepository).save(argumentCaptor.capture());
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
}