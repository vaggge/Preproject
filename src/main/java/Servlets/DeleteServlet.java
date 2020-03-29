package Servlets;

import DAO.UserDAO;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getAttribute("access") == "false") {
            req.setAttribute("isVisible", true);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
        else {
            userService.deleteUser(req.getParameter("name"));
            req.setAttribute("isVisible", true);
            getServletContext().getRequestDispatcher("/admin").forward(req, resp);
        }
    }
}
