package Servlets;

import Models.User;
import Services.UserService;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("name");
        if (req.getAttribute("access") == "false" || name == null) {
            req.setAttribute("isVisible", true);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
        User user = userService.getUserByName(name);
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
    }
}
