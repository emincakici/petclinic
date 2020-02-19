package com.javaegitimleri.petclinic.security;

import com.javaegitimleri.petclinic.service.PetClinicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by EMINCAKICI on Feb Wed 19,2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
public class PetClinicSecurityWithoutAuthTokenTests {

    @Autowired
    private PetClinicService petClinicService;

    //AuthenticationCredentialsNotFoundException hatasi alindigindan
    // dolayi Test annotation a expected degeri eklendi
    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testFindOwners() {
        petClinicService.findOwners();
    }
}
