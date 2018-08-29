package com.revature.servlet;

import com.revature.bean.Employee;
import com.revature.bean.Request;
import com.revature.util.AuthenticationUtil;
import com.revature.util.DispatcherUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "NewEmployeeServlet", urlPatterns = "/newEmployee")
public class NewEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
//            int employeeID = Integer.parseInt(session.getAttribute("id").toString());
//            double amount = Double.parseDouble(request.getParameter("amount"));
//            String desc = request.getParameter("description");
//            Part file = request.getPart("file");
//            InputStream fileContent = file.getInputStream();
//
//            DispatcherUtil du = new DispatcherUtil();
//
//            du.addRequest(new Request(employeeID, amount, desc), fileContent);
            String username = request.getParameter("username").toLowerCase();
            int password = request.getParameter("password").hashCode();
            String fName = request.getParameter("firstName");
            String lName = request.getParameter("lastName");
            String email = request.getParameter("email");
            boolean isManager = request.getParameter("isManager").equals("isManager");
            DispatcherUtil du = new DispatcherUtil();

            int managedBy;
            if (request.getParameter("managedBy") == null) {
                managedBy = Integer.parseInt(request.getParameter("managedBy"));
                du.addEmployee(new Employee(fName, lName, username, password, email, managedBy, isManager));
            } else {
                managedBy = 0;
                du.addManager(new Employee(fName, lName, username, password, email, managedBy, isManager));
            }



            response.sendRedirect("profile");
        } else {
            response.sendRedirect("login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
//         Check whether a session exists
        if (session != null && session.getAttribute("username") != null) {
            if (AuthenticationUtil.getEmployeeType(session.getAttribute("username").toString())) {
                request.getRequestDispatcher("views/newEmployee.html").forward(request, response);
            } else {
                response.sendError(401, "You do not have sufficient access privileges");
            }
        } else {
            response.sendRedirect("login");
        }
    }
}
