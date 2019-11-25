package com.ght.graduationsys.service.impl;

import com.ght.graduationsys.dao.StudentMapper;
import com.ght.graduationsys.entity.Student;
import com.ght.graduationsys.entity.StudentBasicInfo;
import com.ght.graduationsys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getStudentById(String id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public String getClassById(String id) {
        return studentMapper.getClassById(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public void updatePasswordById(String id, String psw) {
        studentMapper.updatePasswordById(id,psw);
    }

    @Override
    public String getPasswordById(String id) {
        return studentMapper.getPasswordById(id);
    }

    @Override
    public String getDirectorIdById(String studentid) {
        return studentMapper.getDirectorIdById(studentid);
    }

    @Override
    public StudentBasicInfo getStudentBasicInfoById(String id) {
        return studentMapper.getStudentBasicInfoById(id);
    }
}
