package com.sanket.designparkinglot.dtos.displayboard;

import com.sanket.designparkinglot.dtos.base.response.ResponseDto;
import com.sanket.designparkinglot.models.displayboard.DisplayBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateDisplayBoardResponseDto extends ResponseDto {

    private DisplayBoard displayBoard;
}
