package com.revature.servlet;

import com.revature.bean.Employee;
import com.revature.util.AuthenticationUtil;
import com.revature.util.DispatcherUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EditInfoServlet", urlPatterns = "/edit")
public class EditInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Check whether a session exists
        if (session != null && session.getAttribute("username") != null) {
            if (AuthenticationUtil.getEmployeeType(session.getAttribute("username").toString())) {
                DispatcherUtil du = new DispatcherUtil();
                int id = Integer.parseInt(session.getAttribute("id").toString());
                String username = request.getParameter("username").toLowerCase();
                String fName = request.getParameter("firstName");
                String lName = request.getParameter("lastName");
                String email = request.getParameter("email");

                du.updateUser(new Employee(id, fName, lName, username, email));

                session.setAttribute("username", username);
                response.sendRedirect("edit");
            } else {
                response.sendError(401, "You do not have sufficient access privileges");
            }
        } else {
            response.sendRedirect("login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Check whether a session exists
        if (session != null && session.getAttribute("username") != null) {
            if (AuthenticationUtil.getEmployeeType(session.getAttribute("username").toString())) {
                request.getRequestDispatcher("views/edit.html").forward(request, response);
            } else {
                response.sendError(401, "You do not have sufficient access privileges");
            }
        } else {
            response.sendRedirect("login");
        }
    }
}
