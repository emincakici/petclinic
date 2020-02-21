package com.javaegitimleri.petclinic.dao.jpa;

import com.javaegitimleri.petclinic.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EMINCAKICI on Feb Fri 21,2020
 */
public interface VetRepository extends JpaRepository<Vet, Long> {
}
