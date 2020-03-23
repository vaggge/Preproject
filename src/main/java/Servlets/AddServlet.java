package Servlets;

import DAO.UserDAO;
import Models.User;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = new User(name, password);
        boolean registration = userService.addUser(user);
            if(registration){
                req.setAttribute("add", "Add is successful");
            } else {
                req.setAttribute("add", "Add is Unsuccessful");
            }
            getServletContext().getRequestDispatcher("/ShowUsersServlet").forward(req, resp);
    }
}