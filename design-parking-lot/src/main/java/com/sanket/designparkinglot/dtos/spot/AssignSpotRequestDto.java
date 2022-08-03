package com.sanket.designparkinglot.dtos.spot;

import com.sanket.designparkinglot.dtos.base.request.UpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssignSpotRequestDto extends UpdateRequestDto {

    private Long floorId;
    private Long spotId;
}
