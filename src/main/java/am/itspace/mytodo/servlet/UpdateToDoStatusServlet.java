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
import java.util.Date;

@WebServlet(urlPatterns = "/todos/done")
public class UpdateToDoStatusServlet extends HttpServlet {

    private ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String finishedDate = DateUtil.dateToString(new Date());
        ToDo toDo = toDoManager.getById(id);

        if (toDo != null && toDo.getStatus() == Status.NEW) {
            toDo.setStatus(Status.DONE);
            try {
                toDo.setFinishedDate(DateUtil.stringToDate(finishedDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            toDoManager.update(toDo);
            resp.sendRedirect("/todos");
        } else if (toDo != null && toDo.getStatus() == Status.DONE) {
            toDo.setStatus(Status.NEW);
            toDoManager.update(toDo);
            resp.sendRedirect("/todos");
        }
    }
}
