package by.itacademy.servlet;

import by.itacademy.dto.LoginDto;
import by.itacademy.entity.User;
import by.itacademy.service.LoginService;
import by.itacademy.util.JspPathUtil;

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
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<User> user = LoginService.login(new LoginDto(username, password));
        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
            resp.sendRedirect("/abilities");
        } else {
            resp.sendRedirect("/login?username=" + (username != null ? username : ""));
        }
    }
}
