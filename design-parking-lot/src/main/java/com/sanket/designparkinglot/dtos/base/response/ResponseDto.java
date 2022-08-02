package com.sanket.designparkinglot.dtos.base.response;

import lombok.Data;

@Data
public class ResponseDto {

    private ResponseStatus responseStatus;
    private String message;
}
