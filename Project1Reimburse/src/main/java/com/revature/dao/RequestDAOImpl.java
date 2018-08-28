package com.revature.dao;

import com.revature.bean.Employee;
import com.revature.bean.Request;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestDAOImpl implements RequestDAO {

    private String filename = "connection.properties";

    @Override
    public Request getRequestByID(int id) {
        Request r = null;
        PreparedStatement pstmt;

        if (id <= 0) {
            return r;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT REQUEST_ID, EMPLOYEE_ID, STATUS, DESCRIPTION, DATE_TIME, AMOUNT FROM REQUEST WHERE REQUEST_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int reqID = rs.getInt("REQUEST_ID");
                int employeeID = rs.getInt("EMPLOYEE_ID");
                int status = rs.getInt("STATUS");
                String desc = rs.getString("DESCRIPTION");
                LocalDateTime dateTime = rs.getTimestamp("DATE_TIME").toLocalDateTime();
                double amount = rs.getDouble("AMOUNT");
                r = new Request(reqID, employeeID, amount, status, desc, dateTime);
            }

        } catch (SQLException | IOException ex) {
            System.out.println("Cannot Connect");
        }
        return r;
    }

    @Override
    public List<Request> getEmployeeRequestByCode(int employeeID, int statusCode) {
        List<Request> rl = new ArrayList<>();
        PreparedStatement pstmt;

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT REQUEST_ID, EMPLOYEE_ID, STATUS, DESCRIPTION, DATE_TIME, AMOUNT FROM REQUEST WHERE EMPLOYEE_ID = ? AND STATUS = ? ORDER BY DATE_TIME DESC";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, employeeID);
            pstmt.setInt(2, statusCode);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int reqID = rs.getInt("REQUEST_ID");
                int eID = rs.getInt("EMPLOYEE_ID");
                double amount = rs.getDouble("AMOUNT");
                int status = rs.getInt("STATUS");
                String desc = rs.getString("DESCRIPTION");
                LocalDateTime dateTime = rs.getTimestamp("DATE_TIME").toLocalDateTime();
                rl.add(new Request(reqID, eID, amount, status, desc, dateTime));
            }
        } catch (SQLException | IOException e) {
            System.out.println("Cannot Connect");
        }
        return rl;
    }

    @Override
    public List<Request> getAllEmployeeRequest(int employeeID) {
        List<Request> rl = new ArrayList<>();
        PreparedStatement pstmt;

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT REQUEST_ID, EMPLOYEE_ID, STATUS, DESCRIPTION, DATE_TIME, AMOUNT FROM REQUEST WHERE EMPLOYEE_ID = ? ORDER BY DATE_TIME DESC";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, employeeID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int reqID = rs.getInt("REQUEST_ID");
                int eID = rs.getInt("EMPLOYEE_ID");
                double amount = rs.getDouble("AMOUNT");
                int status = rs.getInt("STATUS");
                String desc = rs.getString("DESCRIPTION");
                LocalDateTime dateTime = rs.getTimestamp("DATE_TIME").toLocalDateTime();
                rl.add(new Request(reqID, eID, amount, status, desc, dateTime));
            }
        } catch (SQLException | IOException e) {
            System.out.println("Cannot Connect");
        }
        return rl;
    }

    @Override
    public List<Request> getAllManagerRequest(int managerID, int statusCode) {
        List<Request> rl = new ArrayList<>();
        PreparedStatement pstmt;
        EmployeeDAO ed = new EmployeeDAOImpl();
        List<Employee> managed = ed.getAllManaged(managerID);

        if (managerID <= 0 || statusCode < 0 || statusCode < 3)

            try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
                for (Employee aManaged : managed) {
                    String sql = "SELECT REQUEST_ID, EMPLOYEE_ID, STATUS, DESCRIPTION, DATE_TIME, AMOUNT FROM REQUEST WHERE EMPLOYEE_ID = ? AND STATUS = ? ORDER BY DATE_TIME DESC";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1, aManaged.getEmployeeId());
                    pstmt.setInt(2, statusCode);
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        int reqID = rs.getInt("REQUEST_ID");
                        int eID = rs.getInt("EMPLOYEE_ID");
                        double amount = rs.getDouble("AMOUNT");
                        int status = rs.getInt("STATUS");
                        String desc = rs.getString("DESCRIPTION");
                        LocalDateTime dateTime = rs.getTimestamp("DATE_TIME").toLocalDateTime();
                        rl.add(new Request(reqID, eID, amount, status, desc, dateTime));
                    }
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                System.out.println("Cannot Connect");
            }
        return rl;
    }

    @Override
    public boolean addRequestWithReceipt(Request r, InputStream blob) {
        PreparedStatement pstmt;
        if (r == null) {
            return false;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "INSERT INTO REQUEST (EMPLOYEE_ID, DESCRIPTION, RECEIPT, DATE_TIME, AMOUNT) VALUES (?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, r.getEmployeeId());
            pstmt.setString(2, r.getDescription());
            pstmt.setBlob(3, blob);
            pstmt.setTimestamp(4, Timestamp.from(Instant.now()));
            pstmt.setDouble(5, r.getAmount());

            if (pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println("Cannot Connect");
        }
        return false;
    }

    @Override
    public boolean addReceipt(int requestID, InputStream blob) {
        PreparedStatement pstmt;
        if (requestID <= 0) {
            return false;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "UPDATE REQUEST SET RECEIPT = ? WHERE REQUEST_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setBlob(1, blob);
            pstmt.setInt(2, requestID);

            if (pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException | IOException e) {
            System.out.println("Cannot Connect");
        }
        return false;
    }

    @Override
    public boolean updateStatus(int requestID, int newStatus) {
        PreparedStatement pstmt;
        if (requestID < 0 || newStatus < 0 || newStatus > 3) {
            return false;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "UPDATE REQUEST SET STATUS = ? WHERE REQUEST_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, newStatus);
            pstmt.setInt(2, requestID);

            if (pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException | IOException e) {
            System.out.println("Cannot Connect");
        }

        return false;
    }

    @Override
    public byte[] getReceipt(int requestID) {
        PreparedStatement pstmt;
        byte[] image = null;

        if (requestID < 0) {
            return image;
        }

        try (Connection con = ConnectionUtil.getConnectionFromFile(filename)) {
            String sql = "SELECT RECEIPT FROM REQUEST WHERE REQUEST_ID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, requestID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                image = rs.getBytes("RECEIPT");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
