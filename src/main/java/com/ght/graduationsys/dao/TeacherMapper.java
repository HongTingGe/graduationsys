package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.Student;
import com.ght.graduationsys.entity.TaskItem;
import com.ght.graduationsys.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    public List<Teacher> getTeacherByMid(int mid);
    public Teacher getTeacherById(String id);
    public String getTeacherNameById(String id);
    public void updateTeacher(Teacher teacher);
    public String getPasswordById(String id);
    public void updatePasswordById(@Param("id") String id, @Param("psw") String psw);
    public List<TaskItem> getAllTaskItemsByTid(String id);
}
