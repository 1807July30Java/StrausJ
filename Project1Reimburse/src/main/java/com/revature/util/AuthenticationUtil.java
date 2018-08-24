package com.revature.util;

import com.revature.bean.Employee;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;

public class AuthenticationUtil {
    public static boolean isValidUser(String username, String password) {
        EmployeeDAO ed = new EmployeeDAOImpl();
        return ed.getEmployeeByUNamePass(username, password) != null;
    }

    public static boolean getEmployeeType(String username) {
        EmployeeDAO ed = new EmployeeDAOImpl();
        Employee e = ed.getEmployeeByUName(username);
        return e.isManager();
    }
}
