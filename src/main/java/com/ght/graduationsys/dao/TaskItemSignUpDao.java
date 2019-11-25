package com.ght.graduationsys.dao;


import com.ght.graduationsys.entity.StudentBasicInfo;
import com.ght.graduationsys.entity.TaskItemSignUpBasicInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskItemSignUpDao {
    public TaskItemSignUpBasicInfo getTaskItemSignUpByStudentid(String studentid);
    public void addTaskItemSignUp(TaskItemSignUpBasicInfo taskItemSignUpBasicInfo);
    //查找小组成员，去除自己
    public List<String> getStudentIdsByDeclareId(@Param("declareid") String declareid,@Param("myid") String myid);

    //根据系主任id获取课题报名
    public List<TaskItemSignUpBasicInfo> getTaskItemSignUpByDirectorid(String directorid);

    //根据系主任id获取未审批课题报名
    public List<TaskItemSignUpBasicInfo> getUncheckedTaskItemSignUpByDirectorid(String directorid);

    //根据系主任id获取已审批课题报名
    public List<TaskItemSignUpBasicInfo> getCheckedTaskItemSignUpByDirectorid(String directorid);

    public List<StudentBasicInfo> getMembersByDeclareid(String declareid);

    public void successTaskItemSignUpByDeclareid(@Param("declareid") String declareid,@Param("msg") String msg,@Param("teacherid") String teacherid);

    public void failTaskItemSignUpByDeclareid(@Param("declareid") String declareid,@Param("msg") String msg,@Param("teacherid") String teacherid);

    public void deleteTaskItemSignUpByDeclareid(String id);

    public void deleteTaskItemSignUpByStudentId(String id);




}
