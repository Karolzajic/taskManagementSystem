package sk.streetofcode.taskmanagementsystem.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskAddRequest {
    private long userId;
    private Long procejtId;
    private String name;
    private String description;

}
