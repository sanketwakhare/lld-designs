package com.sanket.designsplitwise.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_profile")
public class User extends BaseModel {

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    // hashed password with BCrypt encoding
    private String password;

    @OneToMany(mappedBy = "user")
    private List<GroupParticipant> participatingGroups;

    @OneToMany(mappedBy = "user")
    private List<GroupAdmin> adminGroups;

    @OneToMany(mappedBy = "user")
    private List<ExpensePayingUser> paidExpenses;

    @OneToMany(mappedBy = "user")
    private List<ExpenseOwningUser> ownedExpenses;

}
