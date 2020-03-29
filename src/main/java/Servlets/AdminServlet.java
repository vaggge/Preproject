package Servlets;

import Models.User;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getAttribute("access") == "false") {
            req.setAttribute("isVisible", true);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
        else {
            HttpSession httpSession = req.getSession();
            String name = (String) httpSession.getAttribute("name");
            String password = (String) httpSession.getAttribute("password");
            String role = (String) httpSession.getAttribute("role");
            List<User> users = userService.getAllUsers();
            req.setAttribute("users", users);
            req.setAttribute("user", new User(name, password, role));
            getServletContext().getRequestDispatcher("/admin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
