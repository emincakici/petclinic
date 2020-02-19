package com.javaegitimleri.petclinic.security;

import com.javaegitimleri.petclinic.service.PetClinicService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by EMINCAKICI on Feb Wed 19,2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
public class PetClinicSecurityWithInvalidAuthTokenTests {

    @Autowired
    private PetClinicService petClinicService;

    @Before
    public void setUp() {
        TestingAuthenticationToken auth = new TestingAuthenticationToken("user", "secret", "ROLE_XXX");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    /*SecurityContext te valid bir authantication token i mevcut ise token calistiktan sonra temizlenmesi gerekir.
    Eger temizlenmezse diger test metodari tarafindan da erisilebilir olacak. Bu nedenle tearDown metodu ekledik.*/
    @After
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

    //Token mevcut fakat duzgun rol ile erisim mevcut olmadigindan AccessDeniedException aldik.
    // Bu nedenle expected=AccessDenied.class degerini ekliyoruz
    @Test(expected = AccessDeniedException.class)
    public void testFindOwners() {
        petClinicService.findOwners();
    }
}
