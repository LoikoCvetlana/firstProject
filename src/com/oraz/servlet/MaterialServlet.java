package com.oraz.servlet;

import com.oraz.service.MaterialService;
import com.oraz.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/material")
public class MaterialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("materials", MaterialService.getInstance().findAll());
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("material"))
                .forward(req, resp);
    }
}
