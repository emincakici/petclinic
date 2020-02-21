package com.javaegitimleri.petclinic.security;

import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.service.PetClinicService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by EMINCAKICI on Feb Wed 19,2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
public class PetClinicSecurityWithValidAuthTokenTests {

    @Autowired
    private PetClinicService petClinicService;

    //Token ve duzgun authantication rolu mevcut oldugundan testFindOwners metoduna eristik.
    @Before
    public void setUp() {
        TestingAuthenticationToken auth = new TestingAuthenticationToken("user", "secret", "ROLE_USER");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    /*SecurityContext te valid bir authantication token i mevcut ise token calistiktan sonra temizlenmesi gerekir.
    Eger temizlenmezse diger test metodari tarafindan da erisilebilir olacak. Bu nedenle tearDown metodu ekledik.*/
    @After
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testFindOwners() {
        List<Owner> owners = petClinicService.findOwners();
        MatcherAssert.assertThat(owners.size(), CoreMatchers.equalTo(10));
    }
}
