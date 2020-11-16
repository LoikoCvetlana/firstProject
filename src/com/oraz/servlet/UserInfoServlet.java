package com.oraz.servlet;


import com.oraz.dto.user.UserFullDto;
import com.oraz.service.UserService;
import com.oraz.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/user-info")
public class UserInfoServlet extends HttpServlet {

       @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String UserId = req.getParameter("id");
        UserFullDto user = UserService.getInstance().findById(Long.valueOf(UserId));
        req.setAttribute("user", user);

        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("user-info"))
                .forward(req, resp);
    }
}
