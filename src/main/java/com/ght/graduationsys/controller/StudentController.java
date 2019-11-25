package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.JsonData;
import com.ght.graduationsys.entity.Student;
import com.ght.graduationsys.service.StudentService;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/update/info")
    @ResponseBody
    public Student UpdateStudent(Student student){
        studentService.updateStudent(student);
        return studentService.getStudentById(student.getId());
    }

    @RequestMapping("/update/password")
    @ResponseBody
    public JsonData updatePasswordById(String id,String password){
        password=new SimpleHash("md5",password,null,2).toString();
        studentService.updatePasswordById(id,password);
        return JsonData.buildSuccess("密码修改成功,请重新登录!");
    }

    @RequestMapping("/check/password")
    @ResponseBody
    public JsonData checkPassword(String id,String password){
        password=new SimpleHash("md5",password,null,2).toString();
        String psw1=studentService.getPasswordById(id);
        if (password.equals(psw1)){
            return JsonData.buildSuccess();
        }else{
            return JsonData.buildError("当前密码错误!");
        }
    }

}
