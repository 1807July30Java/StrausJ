package com.revature.servlet;

import com.revature.util.DispatcherUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DataServlet", urlPatterns = "/data")
public class DataServlet extends HttpServlet {

    private DispatcherUtil dispatch = new DispatcherUtil();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Check whether a session exists
        if (session != null && session.getAttribute("username") != null) {
            String entity = request.getParameter("entity");
            String get = request.getParameter("get");

            if (entity != null && get != null) {
                response.setContentType("application/json");
                response.getWriter().write(dispatch.processGet(entity, get));
            }
        } else {
            response.sendError(403, "Stay out of the Forbidden Forest, Harreh!");
        }
    }
}
