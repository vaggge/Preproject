package Servlets;

import DAO.UserDAO;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    UserService userService = new UserService(UserDAO.getJDBCDao());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.deleteUser(req.getParameter("name"));
        req.setAttribute("isVisible", true);
        getServletContext().getRequestDispatcher("/ShowUsersServlet").forward(req, resp);
    }
}
