package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.TaskItem;
import com.ght.graduationsys.entity.TaskItemDeclare;
import com.ght.graduationsys.entity.TaskItemDeclareJsonData;
import com.ght.graduationsys.entity.User;
import com.ght.graduationsys.service.TaskItemDeclareService;
import com.ght.graduationsys.service.TaskItemService;
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
public class DirectorTaskitemController {

    @Autowired
    private TaskItemService taskItemService;

    @Autowired
    private HttpSession session;

    @Autowired
    private TaskItemDeclareService taskItemDeclareService;



    //课题申报审核页面
    @RequestMapping("/taskitem/declare/check/page")
    public String directorTaskitemDeclareCheckPage(){
        return "director-taskitemdeclare-check";
    }

    //所有课题申报对象
    @RequestMapping("/check/taskitem/declare")
    @ResponseBody
    public TaskItemDeclareJsonData directorCheckTaskitemDeclares(int page,int limit){
        User user = (User) session.getAttribute("user");
        Page<TaskItemDeclare> pageInfo= PageHelper.startPage(page,limit);
        List<TaskItemDeclare> taskItemDeclareList= taskItemDeclareService.getTaskItemDeclaresByDirectorId(user.getId());
        return new TaskItemDeclareJsonData(pageInfo.getTotal(),taskItemDeclareList);
    }

    //所有已审核课题申报对象
    @RequestMapping("/checked/taskitem/declare")
    @ResponseBody
    public TaskItemDeclareJsonData directorCheckedTaskitemDeclares(int page,int limit){
        User user = (User) session.getAttribute("user");
        Page<TaskItemDeclare> pageInfo= PageHelper.startPage(page,limit);
        List<TaskItemDeclare> taskItemDeclareList= taskItemDeclareService.getCheckedTaskItemDeclaresByDirectorId(user.getId());
        return new TaskItemDeclareJsonData(pageInfo.getTotal(),taskItemDeclareList);
    }

    //所有未审核课题申报对象
    @RequestMapping("/unchecked/taskitem/declare")
    @ResponseBody
    public TaskItemDeclareJsonData directorUnCheckedTaskitemDeclares(int page,int limit){
        User user = (User) session.getAttribute("user");
        Page<TaskItemDeclare> pageInfo= PageHelper.startPage(page,limit);
        List<TaskItemDeclare> taskItemDeclareList= taskItemDeclareService.getUnCheckedTaskItemDeclaresByDirectorId(user.getId());
        return new TaskItemDeclareJsonData(pageInfo.getTotal(),taskItemDeclareList);
    }


    //待审核页面
    @RequestMapping("/taskitem/unchecked")
    public String directorCheckPage(){
        return "director-taskitem-unchecked";
    }


    //已审核页面
    @RequestMapping("/taskitem/checked")
    public String directorCheckedPage(){
        return "director-taskitem-checked";
    }


    //返回待审核课题List对象
    @RequestMapping("/taskitem/unchecked/list")
    @ResponseBody
    public List<TaskItem> getTaskItemsByDirectorId(String id){
        return taskItemService.getTaskItemsByDirectorId(id);
    }


    //返回已审核课题List对象
    @RequestMapping("/taskitem/checked/list")
    @ResponseBody
    public List<TaskItem> getCheckedTaskItemsByDirectorId(String id){
        return taskItemService.getCheckedTaskItemsByDirectorId(id);
    }


    //更新审核课题状态
    @RequestMapping("/taskitem/update")
    @ResponseBody
    public void updateTaskItemById(TaskItem taskItem){
        taskItemService.updateTaskItemById(taskItem);
    }


    //具体某课题未审核页面
    @RequestMapping("/taskitem/info/unchecked")
    public String taskitemInfoPage(){
        return "director-taskitem-info-unchecked";
    }


    //具体某课题已审核页面
    @RequestMapping("/taskitem/info/checked")
    public String taskitemInfoCheckedPage(){
        return "director-taskitem-info-checked";
    }

}
