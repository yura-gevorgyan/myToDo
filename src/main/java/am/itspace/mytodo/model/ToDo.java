package am.itspace.mytodo.model;

import am.itspace.mytodo.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDo {

    private int id;
    private String title;
    private Date createdDate;
    private Date finishedDate;
    private Status status;
    private User user;

}
