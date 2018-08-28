package com.revature.servlet;

import com.revature.bean.Request;
import com.revature.util.AuthenticationUtil;
import com.revature.util.DispatcherUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@MultipartConfig
@WebServlet(name = "RequestServlet", urlPatterns = "/newRequest")
public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            int employeeID = Integer.parseInt(session.getAttribute("id").toString());
            double amount = Double.parseDouble(request.getParameter("amount"));
            String desc = request.getParameter("description");
            Part file = request.getPart("file");
            InputStream fileContent = file.getInputStream();

            DispatcherUtil du = new DispatcherUtil();

            du.addRequest(new Request(employeeID, amount, desc), fileContent);
            response.sendRedirect("profile");
        } else {
            response.sendRedirect("login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
//         Check whether a session exists
        if (session != null && session.getAttribute("username") != null) {
            request.getRequestDispatcher("views/newRequest.html").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }
}
