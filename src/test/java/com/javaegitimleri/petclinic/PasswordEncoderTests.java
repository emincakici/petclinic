package com.javaegitimleri.petclinic;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.awt.image.ByteComponentRaster;

/**
 * Created by EMINCAKICI on Feb Mon 17,2020
 */

public class PasswordEncoderTests {

    @Test
    public void generateEncodePasswords(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));
        System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));
        System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));
    }
}
