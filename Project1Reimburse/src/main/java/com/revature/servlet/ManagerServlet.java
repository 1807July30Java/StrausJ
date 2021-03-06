package com.revature.servlet;

import com.revature.util.AuthenticationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ManagerServlet", urlPatterns = "/managerProfile")
public class ManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Check whether a session exists
        if (session != null && session.getAttribute("username") != null) {
            if (AuthenticationUtil.getEmployeeType(session.getAttribute("username").toString())) {
                request.getRequestDispatcher("views/managerProfile.html").forward(request, response);
            } else {
                response.sendError(401, "You do not have sufficient access privileges");
            }
        } else {
            response.sendRedirect("login");
        }
    }
}
