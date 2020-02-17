package com.javaegitimleri.petclinic.dao.mem;

import com.javaegitimleri.petclinic.dao.OwnerRepository;
import com.javaegitimleri.petclinic.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by EMINCAKICI on Feb Sun,2020
 */
@Repository
public class OwnerRepositoryInMemoryImpl implements  OwnerRepository{

    private Map<Long, Owner> ownerMap = new HashMap<>();
    public OwnerRepositoryInMemoryImpl() {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Emin");
        owner1.setLastName("Cakici");

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Kenan");
        owner2.setLastName("Sevindik");

        ownerMap.put(owner1.getId(),owner1);
        ownerMap.put(owner2.getId(),owner2);
    }

    @Override
    public List<Owner> findAll() {
       return new ArrayList<>(ownerMap.values());
    }

    @Override
    public Owner findById(Long id) {
        return ownerMap.get(id);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return ownerMap.values().stream().filter(o->o.getLastName().equals(lastName)).collect(Collectors.toList());
    }

    @Override
    public void create(Owner owner) {
        owner.setId(new Date().getTime());
        ownerMap.put(owner.getId(),owner);
    }

    @Override
    public Owner update(Owner owner) {
        ownerMap.replace(owner.getId(),owner);
        return owner;
    }

    @Override
    public void delete(Long id) {
        ownerMap.remove(id);
    }
}
