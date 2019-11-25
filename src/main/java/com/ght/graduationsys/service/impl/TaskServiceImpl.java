package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.TaskMapper;
import com.ght.graduationsys.entity.Task;
import com.ght.graduationsys.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Task> getAllTasks() {
        return taskMapper.getAllTasks();
    }

    @Override
    public List<Task> getTasksByMajorId(int majorid) {
        return taskMapper.getTasksByMajorId(majorid);
    }

    @Override
    public void updateTaskById(Task task) {
        taskMapper.updateTaskById(task);
    }

    @Override
    public void deleteTaskById(int id) {
        taskMapper.deleteTaskById(id);
    }

    @Override
    public void addTask(Task task) {
        taskMapper.addTask(task);
    }
}
