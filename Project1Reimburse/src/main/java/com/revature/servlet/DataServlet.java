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
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            String entity = request.getParameter("entity");
            String get = request.getParameter("get");
            int id = Integer.parseInt(request.getParameter("id"));
            int newCode = Integer.parseInt(request.getParameter("newCode"));


            if (entity != null && get != null) {
                response.setContentType("application/json");
                dispatch.processPost(entity, get, id, newCode);
            }
        } else {
            response.sendError(403, "Stay out of the Forbidden Forest, Harreh!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // Check whether a session exists
        if (session != null && session.getAttribute("username") != null) {
            String entity = request.getParameter("entity");
            String get = request.getParameter("get");

            if (entity != null && get != null) {
                response.setContentType("application/json");
                response.getWriter().write(dispatch.processGet(entity, get, session.getAttribute("username").toString()));
            }
        } else {
            response.sendError(403, "Stay out of the Forbidden Forest, Harreh!");
        }
    }
}
