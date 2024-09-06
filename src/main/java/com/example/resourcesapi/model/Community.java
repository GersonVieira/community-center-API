package com.example.resourcesapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Community {

    private String id;
    private String name;
    private String Address;
    private Integer maxOccupation;
    private Integer currentlyOccupation;
    private Boolean highOccupation;
}
