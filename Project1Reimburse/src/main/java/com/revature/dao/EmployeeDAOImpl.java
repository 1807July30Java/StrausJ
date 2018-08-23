package com.revature.dao;

import com.revature.bean.Employee;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private String filename = "src/main/resources/connection.properties";

    @Override
    public Employee getEmployeeByID(int employeeId) {
        Employee e = null;
        PreparedStatement pstmt;

        if (employeeId <= 0) {
            return e;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                int employeeID = rs.getInt("EMPLOYEE_ID");
                String f_name = rs.getString("F_NAME");
                String l_name = rs.getString("L_NAME");
                String u_name = rs.getString("USERNAME");
                String pass = rs.getString("PASSWORD");
                String email = rs.getString("EMAIL");
                int managed_by = rs.getInt("MANAGED_BY");
                boolean isManager = rs.getBoolean("IS_MANAGER");
                e = new Employee(employeeID, f_name, l_name, u_name, pass, email, managed_by, isManager);
            }

        } catch (SQLException | IOException ex) {
            System.out.println("Cannot Connect");
        }
        return e;
    }

    @Override
    public Employee getEmployeeByUName(String employeeUsername, String employeePass) {
        return null;
    }

    @Override
    public boolean addEmployee(Employee e) {
        return false;
    }

    @Override
    public List<Employee> getAllManaged(Employee manager) {
        return null;
    }
}
