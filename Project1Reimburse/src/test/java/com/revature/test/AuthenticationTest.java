package com.revature.test;

import com.revature.util.AuthenticationUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticationTest {

    @Test
    public void testValidUserTrue() {
        assertTrue(AuthenticationUtil.isValidUser("Jstraus", "testPass"));
    }

    @Test
    public void testWrongPassFalse() {
        assertFalse(AuthenticationUtil.isValidUser("Jstraus", "WrongPass"));
    }

    @Test
    public void testNullUser() {
        assertFalse(AuthenticationUtil.isValidUser(null, null));
    }

    @Test
    public void testIsManagerTrue() {
        assertTrue(AuthenticationUtil.getEmployeeType("Jstraus"));
    }

    @Test
    public void testIsManagerFalse() {
        assertFalse(AuthenticationUtil.getEmployeeType("lrenzullo"));
    }

    @Test
    public void testIsManagerNull() {
        assertFalse(AuthenticationUtil.getEmployeeType(null));
    }

    @Test
    public void testIsManagerInvalidUser() {
        assertFalse(AuthenticationUtil.getEmployeeType("fdasga"));
    }

    @Test
    public void testGetIDValidUser() {
        assertEquals(22, AuthenticationUtil.getEmployeeId("Jstraus"));
    }

    @Test
    public void testGetMissingUser() {
        assertEquals(0, AuthenticationUtil.getEmployeeId("asdad"));
    }

    @Test
    public void testGetNullUser() {
        assertEquals(0, AuthenticationUtil.getEmployeeId(null));
    }
}
