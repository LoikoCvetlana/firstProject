package com.oraz.servlet;

import com.oraz.dto.user.UserBasicDto;
import com.oraz.service.UserService;
import com.oraz.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserBasicDto> users = UserService.getInstance().findAll();
        req.setAttribute("users", users);
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("users"))
                .forward(req, resp);
    }
}
