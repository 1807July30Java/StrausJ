package com.revature.dao;

import com.revature.bean.Employee;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private String filename = "connection.properties";

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

            if (rs.next()) {
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
    public Employee getEmployeeByUNamePass(String employeeUsername, String employeePass) {
        Employee e = null;

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ? AND PASSWORD = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, employeeUsername.toLowerCase());
            stmt.setInt(2, employeePass.hashCode());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("EMPLOYEE_ID");
                String fName = rs.getString("F_NAME");
                String lName = rs.getString("L_NAME");
                String uName = rs.getString("USERNAME");
                int password = rs.getInt("PASSWORD");
                String email = rs.getString("EMAIL");
                int manageBy = rs.getInt("MANAGED_BY");
                boolean isManager = rs.getBoolean("IS_MANAGER");
                e = new Employee(id, fName, lName, uName, password, email, manageBy, isManager);
            } else {
                System.out.println("No users with that username/password combo");
            }
            con.close();
            return e;
        } catch (SQLException | IOException e1) {
            e1.printStackTrace();
        }
        return e;
    }

    @Override
    public Employee getEmployeeByUName(String employeeUsername) {
        Employee e = null;

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, employeeUsername.toLowerCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("EMPLOYEE_ID");
                String fName = rs.getString("F_NAME");
                String lName = rs.getString("L_NAME");
                String uName = rs.getString("USERNAME");
                int password = rs.getInt("PASSWORD");
                String email = rs.getString("EMAIL");
                int manageBy = rs.getInt("MANAGED_BY");
                boolean isManager = rs.getBoolean("IS_MANAGER");
                e = new Employee(id, fName, lName, uName, password, email, manageBy, isManager);
            } else {
                System.out.println("No users with that username/password combo");
            }
            con.close();
            return e;
        } catch (SQLException | IOException e1) {
            e1.printStackTrace();
        }
        return e;
    }

    @Override
    public boolean addEmployee(Employee e) {
        PreparedStatement pstmt;
        if (e == null) {
            return false;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "INSERT INTO EMPLOYEE (F_NAME, L_NAME, USERNAME, PASSWORD, EMAIL, MANAGED_BY, IS_MANAGER) VALUES (?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, e.getFirstName());
            pstmt.setString(2, e.getLastName());
            pstmt.setString(3, e.getUsername());
            pstmt.setInt(4, e.getPassword());
            pstmt.setString(5, e.getEmail());
            pstmt.setInt(6, e.getManagedBy());
            pstmt.setBoolean(7, e.isManager());

            if (pstmt.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Employee> getAllManaged(int managerID) {
        PreparedStatement pstmt;
        List<Employee> employees = new ArrayList<>();

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT * FROM EMPLOYEE WHERE MANAGED_BY = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, managerID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("EMPLOYEE_ID");
                String fName = rs.getString("F_NAME");
                String lName = rs.getString("L_NAME");
                String uName = rs.getString("USERNAME");
                String email = rs.getString("EMAIL");
                boolean isManager = rs.getBoolean("IS_MANAGER");
                employees.add(new Employee(id, fName, lName, uName, email, isManager));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public boolean updateEmployee(Employee e) {
        PreparedStatement pstmt;

        if (e == null) {
            return false;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "UPDATE EMPLOYEE SET F_NAME = ?, L_NAME = ?, USERNAME = ?, EMAIL = ? WHERE EMPLOYEE_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, e.getFirstName());
            pstmt.setString(2, e.getLastName());
            pstmt.setString(3, e.getUsername());
            pstmt.setString(4, e.getEmail());
            pstmt.setInt(5, e.getEmployeeId());

            if (pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException | IOException e1) {
            e1.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Employee> getManagers() {
        PreparedStatement pstmt;
        List<Employee> employees = new ArrayList<>();

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT * FROM EMPLOYEE WHERE IS_MANAGER = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, 1);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("EMPLOYEE_ID");
                String fName = rs.getString("F_NAME");
                String lName = rs.getString("L_NAME");
                String uName = rs.getString("USERNAME");
                String email = rs.getString("EMAIL");
                boolean isManager = rs.getBoolean("IS_MANAGER");
                employees.add(new Employee(id, fName, lName, uName, email, isManager));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public boolean addManager(Employee e) {
        PreparedStatement pstmt;
        if (e == null) {
            return false;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "INSERT INTO EMPLOYEE (F_NAME, L_NAME, USERNAME, PASSWORD, EMAIL, IS_MANAGER) VALUES (?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, e.getFirstName());
            pstmt.setString(2, e.getLastName());
            pstmt.setString(3, e.getUsername());
            pstmt.setInt(4, e.getPassword());
            pstmt.setString(5, e.getEmail());
            pstmt.setBoolean(6, e.isManager());

            if (pstmt.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
