package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.Task;
import com.ght.graduationsys.entity.TaskItemDeclare;
import com.ght.graduationsys.entity.TaskItemDeclareBasicInfo;

import java.util.List;

public interface TaskItemDeclareService {
    public void addTaskItemDeclare(TaskItemDeclare taskitemDeclare);
    public List<TaskItemDeclare> selectTaskItemByStudentId(String studentid);
    public List<TaskItemDeclare> getTaskItemDeclaresByDirectorId(String directorid);
    public List<TaskItemDeclare> getCheckedTaskItemDeclaresByDirectorId(String directorid);
    public List<TaskItemDeclare> getUnCheckedTaskItemDeclaresByDirectorId(String directorid);
    public TaskItemDeclare getTaskItemDeclareById(String id);
    public void updateTaskItemDeclareById(TaskItemDeclare taskItemDeclare);
    public void studentUpdateTaskItemDeclareById(TaskItemDeclare taskItemDeclare);
    public List<TaskItemDeclare> getSuccessedTaskItemDeclareByStudentid(String studentid);
    public TaskItemDeclareBasicInfo getTaskItemDeclareBasicInfoById(String id);
    public List<Task> getTasksByDirectorId(String directorId);
}
