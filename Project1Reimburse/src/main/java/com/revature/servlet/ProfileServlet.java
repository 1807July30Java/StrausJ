package com.revature.servlet;

import com.revature.util.AuthenticationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Check whether a session exists
        if (session != null && session.getAttribute("username") != null) {
            if (AuthenticationUtil.getEmployeeType(session.getAttribute("username").toString())) {
                request.getRequestDispatcher("/managerProfile").forward(request, response);
            } else {
                request.getRequestDispatcher("/employeeProfile").forward(request, response);
            }
        } else {
            response.sendRedirect("login");
        }
    }
}
