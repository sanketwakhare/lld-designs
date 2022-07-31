package com.sanket.designparkinglot.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Floor extends BaseModel {

    private String floorNumber;
    private List<Spot> spots;
}
