package sk.streetofcode.taskmanagementsystem.api;

import sk.streetofcode.taskmanagementsystem.api.request.ProjectAddRequest;
import sk.streetofcode.taskmanagementsystem.api.request.ProjectEditRequest;
import sk.streetofcode.taskmanagementsystem.domain.Project;

import java.util.List;

public interface ProjectService {
    Project get(long id);
    List<Project> getAll();
    List<Project>getAllByUser(long userId);
    void delete(long id);

    long add(ProjectAddRequest request);
    void edit (long id, ProjectEditRequest request);

}
