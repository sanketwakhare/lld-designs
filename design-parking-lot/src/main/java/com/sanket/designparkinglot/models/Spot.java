package com.sanket.designparkinglot.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Spot extends BaseModel {

    private String spotNumber;
    private SpotType spotType;
    private SpotStatus spotStatus;
}
