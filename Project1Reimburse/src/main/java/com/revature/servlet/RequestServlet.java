package com.revature.servlet;

import com.revature.util.AuthenticationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RequestServlet", urlPatterns = "/newRequest")
public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Check whether a session exists
//        if (session != null && session.getAttribute("username") != null) {
            request.getRequestDispatcher("views/newRequest.html").forward(request, response);
//        } else {
//            response.sendRedirect("login");
//        }
    }
}
