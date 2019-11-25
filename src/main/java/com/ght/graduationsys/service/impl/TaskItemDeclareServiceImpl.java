package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.TaskItemDeclareDao;
import com.ght.graduationsys.entity.Task;
import com.ght.graduationsys.entity.TaskItemDeclare;
import com.ght.graduationsys.entity.TaskItemDeclareBasicInfo;
import com.ght.graduationsys.service.TaskItemDeclareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskItemDeclareServiceImpl implements TaskItemDeclareService {

    @Autowired
    private TaskItemDeclareDao taskItemDeclareDao;

    @Override
    public void addTaskItemDeclare(TaskItemDeclare taskitemDeclare) {
        taskItemDeclareDao.addTaskItemDeclare(taskitemDeclare);
    }

    @Override
    public List<TaskItemDeclare> selectTaskItemByStudentId(String studentid) {
        return taskItemDeclareDao.selectTaskItemByStudentId(studentid);
    }

    @Override
    public List<TaskItemDeclare> getTaskItemDeclaresByDirectorId(String directorid) {
        return taskItemDeclareDao.getTaskItemDeclaresByDirectorId(directorid);
    }

    @Override
    public List<TaskItemDeclare> getCheckedTaskItemDeclaresByDirectorId(String directorid) {
        return taskItemDeclareDao.getCheckedTaskItemDeclaresByDirectorId(directorid);
    }

    @Override
    public List<TaskItemDeclare> getUnCheckedTaskItemDeclaresByDirectorId(String directorid) {
        return taskItemDeclareDao.getUnCheckedTaskItemDeclaresByDirectorId(directorid);
    }

    @Override
    public TaskItemDeclare getTaskItemDeclareById(String id) {
        return taskItemDeclareDao.getTaskItemDeclareById(id);
    }

    @Override
    public void updateTaskItemDeclareById(TaskItemDeclare taskItemDeclare) {
        taskItemDeclareDao.updateTaskItemDeclareById(taskItemDeclare);
    }

    @Override
    public void studentUpdateTaskItemDeclareById(TaskItemDeclare taskItemDeclare) {
        taskItemDeclareDao.studentUpdateTaskItemDeclareById(taskItemDeclare);
    }

    @Override
    public List<TaskItemDeclare> getSuccessedTaskItemDeclareByStudentid(String studentid) {
        return taskItemDeclareDao.getSuccessedTaskItemDeclareByStudentid(studentid);
    }

    @Override
    public TaskItemDeclareBasicInfo getTaskItemDeclareBasicInfoById(String id) {
        return taskItemDeclareDao.getTaskItemDeclareBasicInfoById(id);
    }

    @Override
    public List<Task> getTasksByDirectorId(String directorId) {
        return taskItemDeclareDao.getTasksByDirectorId(directorId);
    }
}
