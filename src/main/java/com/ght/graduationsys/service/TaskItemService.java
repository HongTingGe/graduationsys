package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.TaskItem;

import java.util.List;

public interface TaskItemService {
    public void addTaskItem(TaskItem taskItem);
    public void updateTaskItemById(TaskItem taskItem);
    public void studentUpdateTaskItemById(TaskItem taskItem);
    public List<TaskItem> getTaskItemsByDirectorId(String id);
    public List<TaskItem> getCheckedTaskItemsByDirectorId(String id);
    public TaskItem getTaskItemById(String id);
    public TaskItem getStudentTaskItemBySid(String sid);
}
