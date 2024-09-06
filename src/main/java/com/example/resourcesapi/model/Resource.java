package com.example.resourcesapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Resource {
    private String id;
    private Integer doctor;
    private Integer medKit;
    private Integer voluntary;
    private Integer foodParcel;
    private Integer vehicle;
}
