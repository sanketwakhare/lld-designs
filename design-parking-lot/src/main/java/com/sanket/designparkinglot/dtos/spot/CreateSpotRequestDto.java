package com.sanket.designparkinglot.dtos.spot;

import com.sanket.designparkinglot.dtos.base.request.CreateRequestDto;
import com.sanket.designparkinglot.models.spot.SpotType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateSpotRequestDto extends CreateRequestDto {

    private String spotNumber;
    private SpotType spotType;
    private long floorId;
}
