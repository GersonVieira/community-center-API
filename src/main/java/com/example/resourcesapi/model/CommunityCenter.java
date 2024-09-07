package com.example.resourcesapi.model;

 import lombok.*;
 import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class CommunityCenter {

    private String id;
    private String name;
    private String location;
    private String address;
    private Integer maxOccupation;
    private Integer currentlyOccupation;
    private Resource resources;

}
