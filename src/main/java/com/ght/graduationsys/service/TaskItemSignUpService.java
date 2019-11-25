package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.StudentBasicInfo;
import com.ght.graduationsys.entity.TaskItemSignUpBasicInfo;

import java.util.List;

public interface TaskItemSignUpService {
    public TaskItemSignUpBasicInfo getTaskItemSignUpByStudentid(String studentid);
    public void addTaskItemSignUp(TaskItemSignUpBasicInfo taskItemSignUpBasicInfo);
    public List<String> getStudentIdsByDeclareId(String declareid,String myid);
    //根据系主任id获取课题报名
    public List<TaskItemSignUpBasicInfo> getTaskItemSignUpByDirectorid(String directorid);

    //根据系主任id获取未审批课题报名
    public List<TaskItemSignUpBasicInfo> getUncheckedTaskItemSignUpByDirectorid(String directorid);

    //根据系主任id获取已审批课题报名
    public List<TaskItemSignUpBasicInfo> getCheckedTaskItemSignUpByDirectorid(String directorid);

    public List<StudentBasicInfo> getMembersByDeclareid(String declareid);

    public void successTaskItemSignUpByDeclareid(String declareid,String msg,String teacherid);

    public void failTaskItemSignUpByDeclareid(String declareid,String msg,String teacherid);

    public void deleteTaskItemSignUpByDeclareid(String id);

    public void deleteTaskItemSignUpByStudentId(String id);
}
