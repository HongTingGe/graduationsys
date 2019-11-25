package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMapper {
    public List<Task> getAllTasks();
    public List<Task>getTasksByMajorId(int majorid);
    public void updateTaskById(Task task);
    public void deleteTaskById(int id);
    public void addTask(Task task);
    public Task getTaskById(int id);
}
