package com.revature.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Employee;
import com.revature.bean.Request;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.RequestDAO;
import com.revature.dao.RequestDAOImpl;

import java.io.InputStream;

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
                } else if (get.equals("current")) {
                    Employee e = ed.getEmployeeByUName(username);
                    return od.writeValueAsString(e);
                } else if (get.equals("managers")) {
                    return od.writeValueAsString(ed.getManagers());
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
                    case "allApproved": {
                        return od.writeValueAsString(rd.getAllByCode(2));
                    }
                    case "allDenied": {
                        return od.writeValueAsString(rd.getAllByCode(0));
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

    public void addRequest(Request r, InputStream fs) {
        rd.addRequestWithReceipt(r, fs);
    }

    public void updateUser(Employee e) {
        ed.updateEmployee(e);
    }

    public void addEmployee(Employee e) {
        ed.addEmployee(e);
    }
}
