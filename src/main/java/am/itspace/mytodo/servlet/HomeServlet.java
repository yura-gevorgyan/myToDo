package am.itspace.mytodo.servlet;

import am.itspace.mytodo.manager.ToDoManager;
import am.itspace.mytodo.model.ToDo;
import am.itspace.mytodo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/todos")
public class HomeServlet extends HttpServlet {

    private ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ToDo> toDos = toDoManager.getByUser((User) req.getSession().getAttribute("user"));
        req.setAttribute("toDos", toDos);
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}
