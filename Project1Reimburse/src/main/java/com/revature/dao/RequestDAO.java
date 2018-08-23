package com.revature.dao;

import com.revature.bean.Request;

import java.util.List;

public interface RequestDAO {
    Request getRequestByID(int id);
    List<Request> getAllEmployeeRequest(int employeeID, int statusCode);
    List<Request> getAllManagerRequest(int managerID, int statusCode);
    boolean addRequest(Request r);
}
