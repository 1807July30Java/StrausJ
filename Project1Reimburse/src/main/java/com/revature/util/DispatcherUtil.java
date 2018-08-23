package com.revature.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Employee;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;

public class DispatcherUtil {
    private EmployeeDAO ed = new EmployeeDAOImpl();
    private ObjectMapper od = new ObjectMapper();

    public String processGet(String type, String get, String username) {
        try {
            if (type.equals("employee")) {
                if (get.equals("managed")) {
                    Employee e = ed.getEmployeeByUName(username);
                    return od.writeValueAsString(ed.getAllManaged(e));
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "null";
    }
}
