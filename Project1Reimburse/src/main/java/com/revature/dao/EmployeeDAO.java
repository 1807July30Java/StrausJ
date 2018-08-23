package com.revature.dao;

import com.revature.bean.Employee;

import java.util.List;

public interface EmployeeDAO {
    Employee getEmployeeByID(int employeeId);
    Employee getEmployeeByUNamePass(String employeeUsername, String employeePass);
    Employee getEmployeeByUName(String employeeUsername);
    boolean addEmployee(Employee e);
    List<Employee> getAllManaged(Employee manager);

}
