package com.revature.dao;

import com.revature.bean.Employee;

import java.util.List;

public interface EmployeeDAO {
    Employee getEmployeeByID(int employeeId);
    Employee getEmployeeByUName(String employeeUsername, String employeePass);
    boolean addEmployee(Employee e);
    List<Employee> getAllManaged(Employee manager);

}
