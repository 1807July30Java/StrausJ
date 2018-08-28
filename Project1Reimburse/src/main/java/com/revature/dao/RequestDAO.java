package com.revature.dao;

import com.revature.bean.Request;

import java.io.InputStream;
import java.util.List;

public interface RequestDAO {
    Request getRequestByID(int id);

    List<Request> getEmployeeRequestByCode(int employeeID, int statusCode);

    List<Request> getAllManagerRequest(int managerID, int statusCode);

    List<Request> getAllEmployeeRequest(int employeeID);

    boolean addRequestWithReceipt(Request r, InputStream blob);

    boolean addReceipt(int requestID, InputStream blob);

    boolean updateStatus(int requestID, int newStatus);

    byte[] getReceipt(int requestID);
}
