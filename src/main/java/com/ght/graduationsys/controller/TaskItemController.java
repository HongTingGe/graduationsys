package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.TaskItem;
import com.ght.graduationsys.service.MajorService;
import com.ght.graduationsys.service.TaskItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/taskitem")
@Controller
public class TaskItemController {

    @Autowired
    private TaskItemService taskItemService;


    @RequestMapping("/infoId")
    @ResponseBody
    public TaskItem getTaskitemInfoById(String id){
        return taskItemService.getTaskItemById(id);
    }


}
