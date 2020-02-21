package com.javaegitimleri.petclinic;

import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.model.Vet;
import com.javaegitimleri.petclinic.service.PetClinicService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by EMINCAKICI on Feb Tue 18,2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest("spring.profiles.active=dev")
public class PetClinicIntegrationTests {

    @Autowired
    private PetClinicService petClinicService;

    @Test
    public void testFindOwners() {
        List<Owner> owners = petClinicService.findOwners();
        MatcherAssert.assertThat(owners.size(), CoreMatchers.equalTo(10));
    }

    @Test
    public void testFindVets() {
        List<Vet> vets = petClinicService.findVets();
        MatcherAssert.assertThat(vets.size(), CoreMatchers.equalTo(3));
    }
}
