package com.revature.servlet;

import com.revature.dao.RequestDAO;
import com.revature.dao.RequestDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ImageServlet", urlPatterns = "/image")
public class ImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Check whether a session exists
        if (session != null && session.getAttribute("username") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            RequestDAO rd = new RequestDAOImpl();

            if (id > 0) {
                response.setContentType("image/jpg");
                response.getOutputStream().write(rd.getReceipt(id));
            }
        } else {
            response.sendError(403, "Stay out of the Forbidden Forest, Harreh!");
        }
    }
}
