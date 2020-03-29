package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        httpSession.removeAttribute("role");
        httpSession.removeAttribute("name");
        httpSession.removeAttribute("password");
        httpSession.removeAttribute("user");
        httpSession.removeAttribute("isLogin");
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}
