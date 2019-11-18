package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateServlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {

    private final static String url = "/WEB-INF/jsp/update.jsp";
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Long id = Long.valueOf(req.getParameter("id"));
       User user = userService.getUserById(id);
       req.setAttribute("user", user);
       req.getRequestDispatcher(url).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String newName = req.getParameter("name");
        userService.updateNameById(id, newName);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
