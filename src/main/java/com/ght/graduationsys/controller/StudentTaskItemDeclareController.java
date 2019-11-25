package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.*;
import com.ght.graduationsys.service.TaskItemDeclareService;
import com.ght.graduationsys.service.TaskItemSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentTaskItemDeclareController {

    @Autowired
    private HttpSession session;

    @Autowired
    private TaskItemDeclareService taskItemDeclareService;

    @Autowired
    private TaskItemSignUpService taskItemSignUpService;



    @RequestMapping("/taskitemdeclare/info/check/page")
    public String studentTaskItemDeclareInfoCheckPage(){
        return "student-taskitemdeclare-info-check";
    }

    @RequestMapping("/update/taskitemdeclare")
    @ResponseBody
    public JsonData updateTaskItemDeclareById(TaskItemDeclare taskItemDeclare){
        taskItemDeclareService.studentUpdateTaskItemDeclareById(taskItemDeclare);
        return JsonData.buildSuccess("操作成功!");
    }

    @RequestMapping("/get/successed/taskitemdeclare")
    @ResponseBody
    public JsonData getSuccessedTaskItemDeclareByStudentid(){
        User user=(User)session.getAttribute("user");
        List<TaskItemDeclare> taskItemDeclareList=taskItemDeclareService.getSuccessedTaskItemDeclareByStudentid(user.getId());

        //保存成功申报课题
        List<TaskItemDeclareBasicInfo> taskItemDeclareBasicInfoList=new ArrayList<>();
        if (taskItemDeclareList != null){
            for (TaskItemDeclare taskItemDeclare:taskItemDeclareList){
                TaskItemDeclareBasicInfo taskItemDeclareBasicInfo=new TaskItemDeclareBasicInfo();
                taskItemDeclareBasicInfo.setId(taskItemDeclare.getId());
                taskItemDeclareBasicInfo.setName(taskItemDeclare.getName());
                taskItemDeclareBasicInfoList.add(taskItemDeclareBasicInfo);
            }
        }


        String msg = "";

        if (taskItemDeclareBasicInfoList.size()==0){
            msg = "您暂未有课题申请或申报成功的课题!不能进行课题报名!";
        }

        if (!msg.equals("")){
            return JsonData.buildError(msg);
        }


        return JsonData.buildSuccess(taskItemDeclareBasicInfoList,0);
    }

}
