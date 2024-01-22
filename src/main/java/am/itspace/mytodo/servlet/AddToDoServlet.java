package am.itspace.mytodo.servlet;

import am.itspace.mytodo.manager.ToDoManager;
import am.itspace.mytodo.model.ToDo;
import am.itspace.mytodo.model.User;
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

@WebServlet(urlPatterns = "/todos/add")
public class AddToDoServlet extends HttpServlet {

    private ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String createdDate = DateUtil.dateToString(new Date());
        String finishedDate = req.getParameter("finishedDate");

        try {
            toDoManager.add(ToDo.builder()
                    .title(title)
                    .createdDate(DateUtil.stringToDate(createdDate))
                    .finishedDate(DateUtil.stringToDate(finishedDate))
                    .status(Status.NEW)
                    .user((User) req.getSession().getAttribute("user"))
                    .build());

            resp.sendRedirect("/todos");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
