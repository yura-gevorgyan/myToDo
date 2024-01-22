package am.itspace.mytodo.manager;

import am.itspace.mytodo.db.DBConnectionProvider;
import am.itspace.mytodo.model.ToDo;
import am.itspace.mytodo.model.User;
import am.itspace.mytodo.model.enums.Status;
import am.itspace.mytodo.util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ToDoManager {

    Connection connection = DBConnectionProvider.getInstance().getConnection();

    private UserManager userManager = new UserManager();

    public void add(ToDo toDo) {
        String sql = "INSERT INTO to_do(created_date,finished_date,status,user_id,title) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, DateUtil.dateToString(toDo.getCreatedDate()));
            ps.setString(2, DateUtil.dateToString(toDo.getFinishedDate()));
            ps.setString(3, toDo.getStatus().toString());
            ps.setInt(4, toDo.getUser().getId());
            ps.setString(5, toDo.getTitle());

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);

                toDo.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ToDo> getByUser(User user) {
        String sql = "SELECT * FROM to_do WHERE user_id =" + user.getId();
        List<ToDo> toDoList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String createdDate = resultSet.getString("created_date");
                String finishedDate = resultSet.getString("finished_date");
                String statusStr = resultSet.getString("status");
                String title = resultSet.getString("title");
                Status status = Status.valueOf(statusStr);

                ToDo toDo = ToDo.builder()
                        .id(id)
                        .createdDate(DateUtil.stringToDate(createdDate))
                        .finishedDate(DateUtil.stringToDate(finishedDate))
                        .status(status)
                        .user(user)
                        .title(title)
                        .build();
                toDoList.add(toDo);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return toDoList;
    }

    public void delete(int id) {
        String sql = "DELETE from to_do WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ToDo toDo) {
        String sql = "UPDATE to_do SET created_date = ?,finished_date = ?,title = ?,status = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, DateUtil.dateToString(toDo.getCreatedDate()));
            ps.setString(2, DateUtil.dateToString(toDo.getFinishedDate()));
            ps.setString(3, toDo.getTitle());
            ps.setString(4, toDo.getStatus().toString());
            ps.setInt(5, toDo.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ToDo getById(int id) {
        String sql = "SELECT * FROM to_do WHERE id =" + id;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String createdDate = resultSet.getString("created_date");
                String finishedDate = resultSet.getString("finished_date");
                String statusStr = resultSet.getString("status");
                String title = resultSet.getString("title");
                Status status = Status.valueOf(statusStr);
                int userId = resultSet.getInt("user_id");

                User user = userManager.getById(userId);

                return ToDo.builder()
                        .id(id)
                        .createdDate(DateUtil.stringToDate(createdDate))
                        .finishedDate(DateUtil.stringToDate(finishedDate))
                        .status(status)
                        .user(user)
                        .title(title)
                        .build();

            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
