package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.JsonData;
import com.ght.graduationsys.entity.TaskItemDeclare;
import com.ght.graduationsys.service.TaskItemDeclareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskItemDeclareController {

    @Autowired
    private TaskItemDeclareService taskItemDeclareService;

    @RequestMapping("/get/taskitemdeclare")
    @ResponseBody
    public TaskItemDeclare getTaskItemDeclareById(String id){
        System.out.println(taskItemDeclareService.getTaskItemDeclareById(id));
        return taskItemDeclareService.getTaskItemDeclareById(id);
    }

    @RequestMapping("/update/taskitemdeclare")
    @ResponseBody
    public JsonData updateTaskItemDeclareById(TaskItemDeclare taskItemDeclare){
        taskItemDeclareService.updateTaskItemDeclareById(taskItemDeclare);
        return JsonData.buildSuccess("操作成功!");
    }
}
