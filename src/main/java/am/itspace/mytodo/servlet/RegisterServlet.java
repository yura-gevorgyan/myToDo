package am.itspace.mytodo.servlet;

import am.itspace.mytodo.manager.UserManager;
import am.itspace.mytodo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        User userByEmail = userManager.getByEmail(email);
        if (userByEmail == null) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String password = req.getParameter("password");

            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build();

            userManager.add(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/todos");
        } else {
            req.getSession().setAttribute("registerMsg", "WITH THIS E-MAIL HAVE ALREADY REGISTERED !!!");
            resp.sendRedirect("/main");
        }
    }
}
