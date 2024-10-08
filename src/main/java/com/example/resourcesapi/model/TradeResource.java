package com.example.resourcesapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeResource {
    private String firstCenterId;
    private String secondCenterId;
    private Resource firstCenterResource;
    private Resource secondCenterResource;
    private Date createdAt;
}
