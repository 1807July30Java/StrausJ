package com.revature.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Employee;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.RequestDAO;
import com.revature.dao.RequestDAOImpl;

public class DispatcherUtil {
    private EmployeeDAO ed = new EmployeeDAOImpl();
    private RequestDAO rd = new RequestDAOImpl();
    private ObjectMapper od = new ObjectMapper();

    public String processGet(String type, String get, String username) {
        try {
            if (type.equals("employee")) {
                if (get.equals("managed")) {
                    Employee e = ed.getEmployeeByUName(username);
                    return od.writeValueAsString(ed.getAllManaged(e.getEmployeeId()));
                }
            } else if (type.equals("request")) {
                if (get.equals("open")) {
                    Employee e = ed.getEmployeeByUName(username);
                    return od.writeValueAsString(rd.getAllEmployeeRequest(e.getEmployeeId(), 0));
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "null";
    }
}
