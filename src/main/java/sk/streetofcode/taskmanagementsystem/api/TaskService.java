package sk.streetofcode.taskmanagementsystem.api;
import sk.streetofcode.taskmanagementsystem.api.request.TaskAddRequest;
import sk.streetofcode.taskmanagementsystem.api.request.TaskEditRequest;
import sk.streetofcode.taskmanagementsystem.api.request.UserAddRequest;
import sk.streetofcode.taskmanagementsystem.domain.TaskStatus;
import sk.streetofcode.taskmanagementsystem.domain.Task;

import java.util.List;

public interface TaskService {
    long add(TaskAddRequest request);
    void edit(long taskId, TaskEditRequest request);
    void changeStatus(long id, TaskStatus status);
    void assignProject(long taskId, long projectId);
    void delete(long id);
    Task get(long id);
    List<Task> getAll();
    List<Task>getAllbyUserId(long userId);
    List<Task>getAllByProjectID(long projectId);






}
