package com.javaegitimleri.petclinic.service;

import com.javaegitimleri.petclinic.exception.OwnerNotFoundExceptiıon;
import com.javaegitimleri.petclinic.exception.VetNotFoundException;
import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.model.Vet;

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
    List<Vet> findVets();
    Vet findVet(Long id) throws VetNotFoundException;
}
