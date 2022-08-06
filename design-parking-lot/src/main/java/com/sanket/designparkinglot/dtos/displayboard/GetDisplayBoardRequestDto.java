package com.sanket.designparkinglot.dtos.displayboard;

import com.sanket.designparkinglot.dtos.base.request.ReadRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetDisplayBoardRequestDto extends ReadRequestDto {

    private long displayBoardId;
}
