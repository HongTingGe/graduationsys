package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.Task;

import java.util.List;

public interface TaskService {
    public List<Task> getAllTasks();
    public List<Task>getTasksByMajorId(int majorid);
    public void updateTaskById(Task task);
    public void deleteTaskById(int id);
    public void addTask(Task task);
}
