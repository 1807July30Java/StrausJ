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
                } else if (get.matches("-?\\d+(\\.\\d+)?")) {
                    Employee e = ed.getEmployeeByID(Integer.parseInt(get));
                    return od.writeValueAsString(e);
                }
            } else if (type.equals("request")) {
                switch (get) {
                    case "open": {
                        Employee e = ed.getEmployeeByUName(username);
                        return od.writeValueAsString(rd.getEmployeeRequestByCode(e.getEmployeeId(), 0));
                    }
                    case "all": {
                        Employee e = ed.getEmployeeByUName(username);
                        return od.writeValueAsString(rd.getAllEmployeeRequest(e.getEmployeeId()));
                    }
                    case "managed": {
                        Employee e = ed.getEmployeeByUName(username);
                        return od.writeValueAsString(rd.getAllManagerRequest(e.getEmployeeId(), 1));
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "null";
    }

    public void processPost(String type, String get, int id, int newCode) {
        if (type.equals("request")) {
            if (get.equals("update")) {
                rd.updateStatus(id, newCode);
            }
        }
    }
}
