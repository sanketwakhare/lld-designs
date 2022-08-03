package com.sanket.designparkinglot.dtos.floor;

import com.sanket.designparkinglot.dtos.base.request.UpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AllocateSpotRequestDto extends UpdateRequestDto {

    private Long floorId;
    private Long spotId;
}
