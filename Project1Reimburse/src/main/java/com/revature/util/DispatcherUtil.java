package com.revature.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Employee;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;

public class DispatcherUtil {
    private EmployeeDAO ed = new EmployeeDAOImpl();
    private ObjectMapper od = new ObjectMapper();

    public String processGet(String type, String get) {
        try {
            if (type.equals("employee")) {
                if (get.equals("managed")) {
//                    Employee e = ed.getEmployeeByUName()
//                    return om
                }
            }
        } catch (Exception e){

        }
        return null;
    }
}
