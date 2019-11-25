package com.ght.graduationsys.dao;

import com.ght.graduationsys.entity.Student;
import com.ght.graduationsys.entity.StudentBasicInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    public Student getStudentById(String id);
    public String getClassById(String id);
    public void updateStudent(Student student);
    public String getPasswordById(String id);
    public void updatePasswordById(@Param("id") String id,@Param("psw") String psw);
    public String getDirectorIdById(String studentid);
    public StudentBasicInfo getStudentBasicInfoById(String id);
}
