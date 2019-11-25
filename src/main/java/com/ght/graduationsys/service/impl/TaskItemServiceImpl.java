package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.TaskItemMapper;
import com.ght.graduationsys.entity.TaskItem;
import com.ght.graduationsys.service.TaskItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskItemServiceImpl implements TaskItemService {

    @Autowired
    private TaskItemMapper taskItemMapper;

    @Override
    public void addTaskItem(TaskItem taskItem) {
        taskItemMapper.addTaskItem(taskItem);
    }

    @Override
    public List<TaskItem> getTaskItemsByDirectorId(String id) {
        return taskItemMapper.getTaskItemsByDirectorId(id);
    }

    @Override
    public List<TaskItem> getCheckedTaskItemsByDirectorId(String id) {
        return taskItemMapper.getCheckedTaskItemsByDirectorId(id);
    }

    @Override
    public void updateTaskItemById(TaskItem taskItem) {
        taskItemMapper.updateTaskItemById(taskItem);
    }

    @Override
    public void studentUpdateTaskItemById(TaskItem taskItem) {
        taskItemMapper.studentUpdateTaskItemById(taskItem);
    }

    @Override
    public TaskItem getTaskItemById(String id) {
        return taskItemMapper.getTaskItemById(id);
    }

    @Override
    public TaskItem getStudentTaskItemBySid(String sid) {
        return taskItemMapper.getStudentTaskItemBySid(sid);
    }
}
