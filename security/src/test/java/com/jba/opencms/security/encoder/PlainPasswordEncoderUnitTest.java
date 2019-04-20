package com.jba.opencms.security.encoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PlainPasswordEncoderUnitTest {

    @Test
    public void encodeTest(){
        PlainPasswordEncoder encoder = new PlainPasswordEncoder();

        String password = "TestPassword";

        String encode = encoder.encode(password);

        assertEquals(password, encode);
    }

    @Test
    public void matchesTest(){
        PlainPasswordEncoder encoder = new PlainPasswordEncoder();

        String password = "TestPassword";
        String testPassword = encoder.encode("TestPassword");

        assertEquals(encoder.encode(password), testPassword);
    }

}