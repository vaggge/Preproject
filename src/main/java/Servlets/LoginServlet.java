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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        final String name = req.getParameter("name");
        final String password =req.getParameter("password");
        if (userService.isUserExist(name) && userService.validateUser(name, password)){
            String role = userService.getRole(name, password);
            session.setAttribute("role", role);
            session.setAttribute("name", name);
            session.setAttribute("password", password);
            session.setAttribute("user", userService.getUserByName(name));
            session.setAttribute("isLogin", true);

            if (role.equals("admin")){
                getServletContext().getRequestDispatcher("/admin").forward(req, resp);
            }
            else if (role.equals("user")){
                getServletContext().getRequestDispatcher("/user").forward(req, resp);
            }
        }
        else {
            req.setAttribute("isVisible", true);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
