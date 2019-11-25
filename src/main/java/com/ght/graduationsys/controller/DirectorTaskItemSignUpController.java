package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.*;
import com.ght.graduationsys.service.TaskItemSignUpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/director")
public class DirectorTaskItemSignUpController {

    @Autowired
    private HttpSession session;

    @Autowired
    private TaskItemSignUpService taskItemSignUpService;

    //报名审批页面
    @RequestMapping("/taskitem/signup/check/page")
    public String taskItemSignupPage(){
        return "director-taskitem-signup-check";
    }


    //返回所有课题报名对象
    @RequestMapping("/check/taskitem/signup")
    @ResponseBody
    public TaskItemSignUpJsonData getTaskItemSignUpByDirectorid(int page,int limit){
        User user = (User) session.getAttribute("user");
        Page<TaskItemSignUpBasicInfo> pageInfo= PageHelper.startPage(page,limit);
        List<TaskItemSignUpBasicInfo> signUpList=taskItemSignUpService.getTaskItemSignUpByDirectorid(user.getId());
        return new TaskItemSignUpJsonData(pageInfo.getTotal(),signUpList);

    }

    //返回所有报名成功对象
    @RequestMapping("/checked/taskitem/signup")
    @ResponseBody
    public TaskItemSignUpJsonData getCheckedTaskItemSignUpByDirectorid(int page,int limit){
        User user = (User) session.getAttribute("user");
        Page<TaskItemSignUpBasicInfo> pageInfo= PageHelper.startPage(page,limit);
        List<TaskItemSignUpBasicInfo> signUpList=taskItemSignUpService.getCheckedTaskItemSignUpByDirectorid(user.getId());
        return new TaskItemSignUpJsonData(pageInfo.getTotal(),signUpList);

    }


    //返回所有未审批的报名课题对象
    @RequestMapping("/unchecked/taskitem/signup")
    @ResponseBody
    public TaskItemSignUpJsonData getUncheckedTaskItemSignUpByDirectorid(int page,int limit){
        User user = (User) session.getAttribute("user");
        Page<TaskItemSignUpBasicInfo> pageInfo= PageHelper.startPage(page,limit);
        List<TaskItemSignUpBasicInfo> signUpList=taskItemSignUpService.getUncheckedTaskItemSignUpByDirectorid(user.getId());
        return new TaskItemSignUpJsonData(pageInfo.getTotal(),signUpList);

    }

    //返回报名课题的成员对象
    @RequestMapping("/get/students/bydeclareid")
    @ResponseBody
    public List<StudentBasicInfo> getMembersByDeclareid(String declareid){
        return taskItemSignUpService.getMembersByDeclareid(declareid);
    }


    @RequestMapping("/success/taskItem/signup/bydeclareid")
    @ResponseBody
    public JsonData successTaskItemSignUpByDeclareid(String declareid,String msg,String teacherid){
        try{
            taskItemSignUpService.successTaskItemSignUpByDeclareid(declareid,msg,teacherid);
            return JsonData.buildSuccess("操作成功!");
        }catch (Exception e){
            return JsonData.buildError("操作失败!");
        }
    }


    @RequestMapping("/fail/taskItem/signup/bydeclareid")
    @ResponseBody
    public JsonData failTaskItemSignUpByDeclareid(String declareid,String msg,String teacherid){
        try{
            taskItemSignUpService.failTaskItemSignUpByDeclareid(declareid,msg,teacherid);
            return JsonData.buildSuccess("操作成功!");
        }catch (Exception e){
            return JsonData.buildError("操作失败!");
        }

    }


    @RequestMapping("/get/taskitem/signup/studentid")
    @ResponseBody
    public JsonData getTaskItemSignUpBasicInfoByStudentId(String studentId){
        TaskItemSignUpBasicInfo signUpBasicInfo = taskItemSignUpService.getTaskItemSignUpByStudentid(studentId);
        if (signUpBasicInfo == null){
            return JsonData.buildError("学号:"+studentId+"暂无报名信息!");
        }else {
            return JsonData.buildSuccess(signUpBasicInfo,1);
        }
    }

    @RequestMapping("/delete/taskitem/signup/studentid")
    @ResponseBody
    public JsonData deleteTaskItemSignUpByStudentId(String studentId){
        taskItemSignUpService.deleteTaskItemSignUpByStudentId(studentId);
        return JsonData.buildSuccess("操作成功!");
    }



}
