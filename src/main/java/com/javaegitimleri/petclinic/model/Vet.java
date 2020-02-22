package com.javaegitimleri.petclinic.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by EMINCAKICI on Feb Fri 21,2020
 */

@Entity
@Table(name = "t_vet")
public class Vet extends BaseEntity {
    @NotEmpty
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
