package com.ght.graduationsys.controller;

import com.ght.graduationsys.service.TaskItemDeclareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/director")
public class DirectorTaskItemDeclareController {

    @Autowired
    private TaskItemDeclareService taskItemDeclareService;

    @RequestMapping("/taskitemdeclare/info/check/page")
    public String directorTaskItemDeclareInfoCheckPage(){
        return "director-taskitemdeclare-info-check";
    }

}
