package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.*;
import com.ght.graduationsys.service.*;
import com.ght.graduationsys.util.DateUtil;
import com.ght.graduationsys.util.UUIDUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentTaskItemController {

    @Autowired
    private HttpSession session;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MajorService majorService;
    @Autowired
    private TaskItemService taskItemService;
    @Autowired
    private TaskItemDeclareService taskItemDeclareService;

    @Autowired
    private TaskItemSignUpService taskItemSignUpService;

    //学生课题申报页面
    @RequestMapping("/taskitem/declare/page")
    public String taskItemDeclarePage(){
        return "student-taskitem-declare";
    }

    //学生课题申报
    @RequestMapping("/taskitem/declare")
    @ResponseBody
    public JsonData handleTaskItemDeclare(TaskItemDeclare taskItemDeclare){

        User user= (User) session.getAttribute("user");
        List<TaskItemDeclare> taskItemDeclare1s=taskItemDeclareService.selectTaskItemByStudentId(user.getId());
        if (taskItemDeclare1s!=null){
            if (taskItemDeclare1s.size() == 2){
                return JsonData.buildError("每位学生最多只能申报两个课题!");
            }
        }


        taskItemDeclare.setId(UUIDUtil.getUUID());
        taskItemDeclare.setStudentid(user.getId());
        String directorid=studentService.getDirectorIdById(taskItemDeclare.getStudentid());
        taskItemDeclare.setDirectorid(directorid);
        taskItemDeclare.setCreatetime(DateUtil.getCurrentDate());
        taskItemDeclareService.addTaskItemDeclare(taskItemDeclare);
        return JsonData.buildSuccess("课题申报已提交!");

    }

    //学生课题申报查看
    @RequestMapping("/taskitem/declare/check/page")
    public String taskItemDeclareCheckPage(){
        return "student-taskitem-declare-check";
    }


    //返回学生课题申报对象
    @RequestMapping("/taskitem/declare/check")
    @ResponseBody
    public TaskItemDeclareJsonData getTaskItemDeclareCheck(int page,int limit){
        User user= (User) session.getAttribute("user");
        Page<TaskItemDeclare> pageInfo= PageHelper.startPage(page,limit);
        List<TaskItemDeclare> taskItemDeclares=taskItemDeclareService.selectTaskItemByStudentId(user.getId());
        boolean flag=false;

        for (TaskItemDeclare taskItemDeclare:taskItemDeclares){
            if (taskItemDeclare.getStatus()!=-1){
                flag=true;
                break;
            }
        }
        if (flag==true){
            for (TaskItemDeclare taskItemDeclare:taskItemDeclares){
                taskItemDeclare.setFlag(flag);
            }
        }
        return new TaskItemDeclareJsonData(pageInfo.getTotal(),taskItemDeclares);
    }


    //学生课题申报页面
    @RequestMapping("/taskitem/signup")
    public String taskItemSignUp(){
        return "taskitem-add";
    }


    //返回学生课题对象
    @RequestMapping("/taskitem")
    @ResponseBody
    public TaskItem getStudentTaskItemBySid(String id){
        return taskItemService.getStudentTaskItemBySid(id);
    }



    //学生课题详细信息页面
    @RequestMapping("/taskitem/info/check")
    public String studentTaskItemPage(){
        return "student-taskitem-info-check";
    }


    //学生课题概要页面
    @RequestMapping("taskitem/check")
    public String studentTaskItem(){
        return "student-taskitem-check";
    }


    //学生课题申报
    @RequestMapping("/taskitem/apply")
    @ResponseBody
    public void taskItemApply(TaskItem taskItem){

        System.out.println(taskItem.getMembers());
        taskItem.setId(UUIDUtil.getUUID());
        String directorid=majorService.getDirectorIdByMajorName(taskItem.getMajorClazz().split(" ")[0]);
        taskItem.setDirectorid(directorid);
        taskItem.setStatus(0);
        taskItem.setCreatetime(new Date());
        taskItemService.addTaskItem(taskItem);
    }


    //学生课题重新申报
    @RequestMapping("/taskitem/update")
    @ResponseBody
    public void taskItemUpdateById(TaskItem taskItem){
        taskItemService.studentUpdateTaskItemById(taskItem);

    }



}
