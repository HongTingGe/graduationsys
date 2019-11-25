package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.TaskItemSignUpDao;
import com.ght.graduationsys.entity.StudentBasicInfo;
import com.ght.graduationsys.entity.TaskItemSignUpBasicInfo;
import com.ght.graduationsys.service.TaskItemSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskItemSignUpServiceImpl implements TaskItemSignUpService {

    @Autowired
    private TaskItemSignUpDao taskItemSignUpDao;

    @Override
    public TaskItemSignUpBasicInfo getTaskItemSignUpByStudentid(String studentid) {
        return taskItemSignUpDao.getTaskItemSignUpByStudentid(studentid);
    }

    @Override
    public void addTaskItemSignUp(TaskItemSignUpBasicInfo taskItemSignUpBasicInfo) {
        taskItemSignUpDao.addTaskItemSignUp(taskItemSignUpBasicInfo);
    }

    @Override
    public List<String> getStudentIdsByDeclareId(String declareid,String myid) {
        return taskItemSignUpDao.getStudentIdsByDeclareId(declareid,myid);
    }

    @Override
    public List<TaskItemSignUpBasicInfo> getTaskItemSignUpByDirectorid(String directorid) {
        return taskItemSignUpDao.getTaskItemSignUpByDirectorid(directorid);
    }

    @Override
    public List<TaskItemSignUpBasicInfo> getUncheckedTaskItemSignUpByDirectorid(String directorid) {
        return taskItemSignUpDao.getUncheckedTaskItemSignUpByDirectorid(directorid);
    }

    @Override
    public List<TaskItemSignUpBasicInfo> getCheckedTaskItemSignUpByDirectorid(String directorid) {
        return taskItemSignUpDao.getCheckedTaskItemSignUpByDirectorid(directorid);
    }

    @Override
    public List<StudentBasicInfo> getMembersByDeclareid(String declareid) {
        return taskItemSignUpDao.getMembersByDeclareid(declareid);
    }

    @Override
    public void successTaskItemSignUpByDeclareid(String declareid,String msg,String teacherid){
        taskItemSignUpDao.successTaskItemSignUpByDeclareid(declareid,msg,teacherid);
    }

    @Override
    public void failTaskItemSignUpByDeclareid(String declareid,String msg,String teacherid){
        taskItemSignUpDao.failTaskItemSignUpByDeclareid(declareid, msg, teacherid);
    }

    @Override
    public void deleteTaskItemSignUpByDeclareid(String id) {
        taskItemSignUpDao.deleteTaskItemSignUpByDeclareid(id);
    }

    @Override
    public void deleteTaskItemSignUpByStudentId(String id) {
        taskItemSignUpDao.deleteTaskItemSignUpByStudentId(id);
    }
}
