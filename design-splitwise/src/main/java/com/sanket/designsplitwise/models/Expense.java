package com.sanket.designsplitwise.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "expense")
public class Expense extends BaseModel {

    private String description;

    @ManyToOne
    private User creator;

    @ManyToOne
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(mappedBy = "expense")
    private List<ExpensePayingUser> payingUsers;

    @OneToMany(mappedBy = "expense")
    private List<ExpenseOwningUser> owningUsers;

}
