package com.oraz.servlet.basic;

import com.oraz.dto.LoginDto;
import com.oraz.service.UserService;
import com.oraz.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("login");
        String password = req.getParameter("password");
        Optional user = UserService.login(new LoginDto(username, password));
        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/products");
        } else {
            resp.sendRedirect("/login?username=" + (username != null ? username : ""));
        }
    }
}
