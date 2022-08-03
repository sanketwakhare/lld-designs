package com.sanket.designparkinglot.dtos.spot;

import com.sanket.designparkinglot.dtos.base.response.ResponseDto;
import com.sanket.designparkinglot.models.spot.Spot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateSpotResponseDto extends ResponseDto {

    private Spot spot;
}
