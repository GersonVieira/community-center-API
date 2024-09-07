package com.example.resourcesapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Community {

    private String id;
    private String name;
    private String address;
    private Integer maxOccupation;
    private Integer currentlyOccupation;
    private Boolean highOccupation;
}
