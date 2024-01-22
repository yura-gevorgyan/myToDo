package am.itspace.mytodo.servlet;

import am.itspace.mytodo.manager.ToDoManager;
import am.itspace.mytodo.model.ToDo;
import am.itspace.mytodo.model.enums.Status;
import am.itspace.mytodo.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/todos/update")
public class UpdateToDoServlet extends HttpServlet {

    private ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ToDo toDo = toDoManager.getById(id);
        if (toDo != null) {
            req.setAttribute("toDo", toDo);
            req.getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/todos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String finishedDate = req.getParameter("finishedDate");

        ToDo toDo = toDoManager.getById(id);
        try {
            if (toDo != null) {
                toDo.setTitle(title);
                toDo.setFinishedDate(DateUtil.stringToDate(finishedDate));
                toDo.setStatus(Status.NEW);
                toDoManager.update(toDo);
                resp.sendRedirect("/todos");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

