package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.TaskItem;
import com.ght.graduationsys.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherService {
    public List<Teacher> getTeacherByMid(int mid);
    public Teacher getTeacherById(String id);
    public String getTeacherNameById(String id);
    public void updateTeacher(Teacher teacher);
    public String getPasswordById(String id);
    public void updatePasswordById(String id,String psw);
    public List<TaskItem> getAllTaskItemsByTid(String id);

}
