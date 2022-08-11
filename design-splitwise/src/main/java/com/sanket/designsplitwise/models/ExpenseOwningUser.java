package com.sanket.designsplitwise.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class ExpenseOwningUser extends BaseModel {

    @ManyToOne
    private Expense expense;

    @ManyToOne
    private User user;

    private double amount;
}
