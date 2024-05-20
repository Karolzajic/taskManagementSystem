package sk.streetofcode.taskmanagementsystem.domain;

import java.time.OffsetDateTime;

public class Task {
    private final long id;
    private final long userId;
    private final Long projectID;
    private final String name;
    private final String description;
    private final TaskStatus status;
    private final OffsetDateTime ceatedAt;

    public Task(long id, long userId, Long projectID, String name, String description, TaskStatus status, OffsetDateTime ceatedAt) {
        this.id = id;
        this.userId = userId;
        this.projectID = projectID;
        this.name = name;
        this.description = description;
        this.status = status;
        this.ceatedAt = ceatedAt;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public Long getProjectID() {
        return projectID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public OffsetDateTime getCeatedAt() {
        return ceatedAt;
    }
}
