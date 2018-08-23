package com.revature.util;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;

public class AuthenticationUtil {
    public static boolean isValidUser(String username, String password) {
        EmployeeDAO ed = new EmployeeDAOImpl();
        return ed.getEmployeeByUNamePass(username, password) != null;
    }
}
