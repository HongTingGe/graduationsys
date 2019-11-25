package com.ght.graduationsys.service;

import com.ght.graduationsys.entity.Student;
import com.ght.graduationsys.entity.StudentBasicInfo;

public interface StudentService {
    public Student getStudentById(String id);
    public String getClassById(String id);
    public void updateStudent(Student student);
    public void updatePasswordById(String id, String psw);
    public String getPasswordById(String id);
    public String getDirectorIdById(String studentid);
    public StudentBasicInfo getStudentBasicInfoById(String id);
}
