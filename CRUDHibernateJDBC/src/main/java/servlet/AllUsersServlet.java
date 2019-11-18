package servlet;

import model.User;
import service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AllUsersServlet", urlPatterns = {"/"})
public class AllUsersServlet extends HttpServlet {
    private final static String url = "/WEB-INF/jsp/index.jsp";


    private UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("use", userService.getAllUser());
        req.getRequestDispatcher(url).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        User user = new User(name, email);
        userService.addUser(user);
            doGet(req, resp);
    }

}
