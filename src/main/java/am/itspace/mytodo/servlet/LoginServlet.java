package am.itspace.mytodo.servlet;

import am.itspace.mytodo.manager.UserManager;
import am.itspace.mytodo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = userManager.getByEmail(email);

        String password = req.getParameter("password");

        if (user != null && user.getPassword().equals(password)) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/todos");
        } else {
            req.getSession().setAttribute("loginMsg", "INVALID E-MAIL OR PASSWORD !!!");
            resp.sendRedirect("/main");
        }
    }
}
