package com.javaegitimleri.petclinic.service;

import com.javaegitimleri.petclinic.exception.OwnerNotFoundExceptiıon;
import com.javaegitimleri.petclinic.model.Owner;

import java.util.List;

/**
 * Created by EMINCAKICI on Feb, 2020
 */
public interface PetClinicService {
    List<Owner> findOwners();
    List<Owner> findOwners(String lastName);
    Owner findOwner (Long id) throws OwnerNotFoundExceptiıon;
    void createOwner(Owner owner);
    void updateOwner(Owner owner);
    void deleteOwner(Long id);
}
