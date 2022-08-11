package com.sanket.designsplitwise.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class GroupAdmin extends BaseModel {

    @ManyToOne
    private Group group;

    @ManyToOne
    private User user;
}
