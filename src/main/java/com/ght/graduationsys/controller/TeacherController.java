package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.JsonData;
import com.ght.graduationsys.entity.TaskItem;
import com.ght.graduationsys.entity.Teacher;
import com.ght.graduationsys.service.TeacherService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/pub/teacher/getName")
    @ResponseBody
    public String getTeacherNameById(String id){
        return teacherService.getTeacherNameById(id);
    }

    @PostMapping(value = "/teacher/update/info")
    @ResponseBody
    public Teacher UpdateStudent(Teacher teacher){
        teacherService.updateTeacher(teacher);
        return teacherService.getTeacherById(teacher.getId());
    }

    @RequestMapping("/teacher/update/password")
    @ResponseBody
    public JsonData updatePasswordById(String id, String password){
        password=new SimpleHash("md5",password,null,2).toString();
        teacherService.updatePasswordById(id,password);
        return JsonData.buildSuccess("密码修改成功,请重新登录!");
    }

    @RequestMapping("/teacher/check/password")
    @ResponseBody
    public JsonData checkPassword(String id,String password){
        password=new SimpleHash("md5",password,null,2).toString();
        String psw1=teacherService.getPasswordById(id);
        if (password.equals(psw1)){
            return JsonData.buildSuccess();
        }else{
            return JsonData.buildError("当前密码错误!");
        }
    }

    @RequestMapping("/teacher/get/taskitems")
    @ResponseBody
    public List<TaskItem> getAllTaskItemsByTid(String id){
        return teacherService.getAllTaskItemsByTid(id);
    }


    @RequestMapping("/teacher/preview")
    public String pdfPage(){
        return "preview";
    }



}
