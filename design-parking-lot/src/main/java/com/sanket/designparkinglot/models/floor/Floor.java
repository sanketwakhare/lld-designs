package com.sanket.designparkinglot.models.floor;

import com.sanket.designparkinglot.models.BaseModel;
import com.sanket.designparkinglot.models.spot.Spot;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Floor extends BaseModel {

    private String floorNumber;
    private List<Spot> spots;
}
