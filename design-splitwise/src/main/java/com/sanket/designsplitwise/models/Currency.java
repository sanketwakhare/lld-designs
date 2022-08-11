package com.sanket.designsplitwise.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Currency extends BaseModel {

    @Column(unique = true)
    private String name;
}
