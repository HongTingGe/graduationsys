package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.TaskItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskItemMapper {
    public void addTaskItem(TaskItem taskItem);
    public void updateTaskItemById(TaskItem taskItem);
    public void studentUpdateTaskItemById(TaskItem taskItem);
    public List<TaskItem> getTaskItemsByDirectorId(String id);
    public List<TaskItem> getCheckedTaskItemsByDirectorId(String id);
    public TaskItem getTaskItemById(String id);
    public TaskItem getStudentTaskItemBySid(String sid);
}
