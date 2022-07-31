package com.sanket.designparkinglot.models.operator;

import com.sanket.designparkinglot.models.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Operator extends BaseModel {

    private int operatorId;
    private String name;
}
