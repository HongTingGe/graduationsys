package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.TeacherMapper;
import com.ght.graduationsys.entity.TaskItem;
import com.ght.graduationsys.entity.Teacher;
import com.ght.graduationsys.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> getTeacherByMid(int mid) {
        return teacherMapper.getTeacherByMid(mid);
    }

    @Override
    public Teacher getTeacherById(String id) {
        return teacherMapper.getTeacherById(id);
    }

    @Override
    public String getTeacherNameById(String id) {
        return teacherMapper.getTeacherNameById(id);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
    }

    @Override
    public String getPasswordById(String id) {
        return teacherMapper.getPasswordById(id);
    }

    @Override
    public void updatePasswordById(String id, String psw) {
        teacherMapper.updatePasswordById(id,psw);
    }

    @Override
    public List<TaskItem> getAllTaskItemsByTid(String id) {
        return teacherMapper.getAllTaskItemsByTid(id);
    }
}
