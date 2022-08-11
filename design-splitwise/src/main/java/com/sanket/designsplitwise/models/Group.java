package com.sanket.designsplitwise.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "groups")
public class Group extends BaseModel {

    private String name;

    @Enumerated(EnumType.STRING)
    private GroupType groupType;

    private String description;

    @ManyToOne
    private User creator;

    @OneToMany(mappedBy = "group")
    private List<GroupParticipant> participants;

    @OneToMany(mappedBy = "group")
    private List<GroupAdmin> admins;

    @OneToMany(mappedBy = "group")
    private List<GroupExpense> expenses;
}
