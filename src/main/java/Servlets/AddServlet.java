package Servlets;

import DAO.UserDAO;
import Models.User;
import Services.UserService;
import org.hibernate.annotations.Filter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/add")
public class AddServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        User user = new User(name, password, role);
        boolean registration = false;

        if (role.equals("user") || role.equals("admin")){

            if (req.getAttribute("access") == "false") {
            req.setAttribute("isVisible", true);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
            else {
                 registration = userService.addUser(user);
            }
            if(registration){
                req.setAttribute("add", "Add is successful");
            } else {
                req.setAttribute("add", "Add is unsuccessful");
            }
    }
        else {
            req.setAttribute("isCorrect", false);
        }
        getServletContext().getRequestDispatcher("/admin").forward(req, resp);
    }
}