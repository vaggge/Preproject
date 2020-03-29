package Servlets;

import DAO.UserDAO;
import Services.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    UserService userService = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String role = session.getAttribute("role") == null ? "" : (String) session.getAttribute("role");
        String path = httpServletRequest.getServletPath();

        if (!path.contains("index.jsp") && role.equals("user") || role.equals("") && path.contains("admin")) {
            request.setAttribute("access", "false");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
