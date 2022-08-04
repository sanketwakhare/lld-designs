package com.sanket.designparkinglot.dtos.displayboard;

import com.sanket.designparkinglot.dtos.base.request.CreateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateDisplayBoardRequestDto extends CreateRequestDto {

    private String displayBoardNumber;
}
