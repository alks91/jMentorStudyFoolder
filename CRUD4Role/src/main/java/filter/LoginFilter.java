package filter;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(filterName = "LoginFilter", urlPatterns = "/", servletNames = {"AdminServlet", "UserServlet"})
public class LoginFilter implements Filter {
  private UserService userService = UserService.getInstance();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user;
        HttpSession httpSession = request.getSession();
        if(httpSession != null && httpSession.getAttribute("email") != null && httpSession.getAttribute("password") != null) {
            User.Role role = (User.Role) httpSession.getAttribute("role");
            getMenu(request, response, role);
        } else if(!userService.isExistUser(email) && userService.isAuthUser(email, password)) {
            user = userService.getUserByEmail(email);
            User.Role role = user.getRole();
            request.getSession().setAttribute("email", email);
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("role", role);
            getMenu(request, response, role);
        } else {
            getMenu(request, response, User.Role.ANON);
        }
    }
    private void getMenu(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, User.Role role) throws ServletException, IOException {
        if(role.equals(User.Role.ADMIN)) {
            switch (httpServletRequest.getRequestURI()) {
                case ("/") :
                    httpServletRequest.setAttribute("use", userService.getAllUser());
                    httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(httpServletRequest, httpServletResponse);
                case ("/admin") :
                    httpServletRequest.setAttribute("use", userService.getAllUser());
                    httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(httpServletRequest, httpServletResponse);
                case ("/user") :
                    httpServletRequest.setAttribute("use", userService.getAllUser());
                    httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(httpServletRequest, httpServletResponse);
            }
        } else if(role.equals(User.Role.USER)) {
            httpServletRequest.setAttribute("email", httpServletRequest.getParameter("email"));
            httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
    @Override
    public void destroy() {

    }
}
