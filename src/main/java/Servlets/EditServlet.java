package Servlets;

import DAO.UserDAO;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    UserService userService = UserService.getInstance();
    List<String> editUser = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        editUser.add(req.getParameter("name"));
        req.setAttribute("isEditVisible", true);
        getServletContext().getRequestDispatcher("/ShowUsersServlet").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if (!userService.isUserExist(req.getParameter("newName"))) {
                userService.changeUserName(editUser.get(0), req.getParameter("newName"));
                userService.changeUserPassword(req.getParameter("newName"), req.getParameter("newPassword"));
                editUser.clear();
                req.setAttribute("isSave", true);
            }
            else {
                req.setAttribute("isSave", false);
            }
        getServletContext().getRequestDispatcher("/ShowUsersServlet").forward(req, resp);
    }
}

