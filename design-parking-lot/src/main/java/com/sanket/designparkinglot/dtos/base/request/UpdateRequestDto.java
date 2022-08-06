package com.sanket.designparkinglot.dtos.base.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRequestDto {

    private Date lastModifiedAt;
    private String lastModifiedBy;
}
