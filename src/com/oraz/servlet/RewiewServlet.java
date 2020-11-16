package com.oraz.servlet;

import com.oraz.service.MaterialService;
import com.oraz.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/rewiews")
public class RewiewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("rewiews", MaterialService.getInstance().findAll());
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("rewiew"))
                .forward(req, resp);
    }
}
