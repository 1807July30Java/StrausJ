package com.revature.util;

import com.revature.bean.Employee;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;

public class AuthenticationUtil {
    public static boolean isValidUser(String username, String password) {
        if (username != null && password != null) {
            EmployeeDAO ed = new EmployeeDAOImpl();
            return ed.getEmployeeByUNamePass(username, password) != null;
        } else {
            return false;
        }
    }

    public static boolean isValidUser(String username) {
        if (username != null) {
            EmployeeDAO ed = new EmployeeDAOImpl();
            return ed.getEmployeeByUName(username) != null;
        } else {
            return false;
        }
    }

    public static boolean getEmployeeType(String username) {
        if (isValidUser(username)) {
            EmployeeDAO ed = new EmployeeDAOImpl();
            Employee e = ed.getEmployeeByUName(username);
            return e.isManager();
        } else {
            return false;
        }
    }

    public static int getEmployeeId(String username) {
        if (isValidUser(username)) {
            EmployeeDAO ed = new EmployeeDAOImpl();
            return ed.getEmployeeByUName(username).getEmployeeId();
        } else {
            return 0;
        }
    }
}
