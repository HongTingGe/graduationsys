package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.*;
import com.ght.graduationsys.service.MajorService;
import com.ght.graduationsys.service.StudentService;
import com.ght.graduationsys.service.TaskItemDeclareService;
import com.ght.graduationsys.service.TaskItemSignUpService;
import com.ght.graduationsys.util.DateUtil;
import com.ght.graduationsys.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentTaskItemSignUpController {

    @Autowired
    private HttpSession session;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TaskItemSignUpService taskItemSignUpService;

    @Autowired
    private TaskItemDeclareService taskItemDeclareService;


    @Autowired
    private MajorService majorService;



    //课题报名页面
    @RequestMapping("/taskitem/signup/page")
    public String taskItemSignupPage(){
        return "student-taskitem-signup";
    }

    //课题报名
    @RequestMapping("/taskitem/signUp")
    @ResponseBody
    public JsonData taskItemSignUp(@RequestBody STaskItemSignUp taskItemSignUp){

        User user = (User) session.getAttribute("user");

        TaskItemSignUpBasicInfo mySignUp=taskItemSignUpService.getTaskItemSignUpByStudentid(user.getId());
        if (mySignUp!=null){
            return JsonData.buildError("您已有课题报名中了,不能再次报名",-1);
        }

        List<StudentBasicInfo> members=taskItemSignUp.getMembers();//获取小组成员
        List<String> list1=new ArrayList<>(); //用于判断成员id是否存在
        List<String> list2=new ArrayList<>(); //用于判断成员是否存在课题报名
        List<String> list4=new ArrayList<>(); //用于判断成员是否是同个专业
        List<StudentBasicInfo> list3=new ArrayList<>(); //用于保存满足以上条件的成员
        StudentBasicInfo myself=studentService.getStudentBasicInfoById(user.getId());
        list3.add(myself);

        if (members!=null){
            for (StudentBasicInfo obj1: members){
                if (obj1.getId().equals(user.getId())){
                    return JsonData.buildError("小组成员只能填写除本人以外的学号!");
                }
            }
            for (StudentBasicInfo obj1: members){
                StudentBasicInfo obj2=studentService.getStudentBasicInfoById(obj1.getId());
                if (obj2==null){
                    list1.add(obj1.getId());
                }else{

                    if (obj2.getMajor().equals(user.getMajor().getName())){
                        TaskItemSignUpBasicInfo taskItemSignUpBasicInfo=taskItemSignUpService.getTaskItemSignUpByStudentid(obj2.getId());
                        if (taskItemSignUpBasicInfo!=null){
                            list2.add(obj2.getId());
                        }else{
                            list3.add(obj2);
                        }
                    }else{
                        list4.add(obj2.getId());
                    }


                }
            }
        }

        //list1判断成员id
        if (list1.size()>0){
            return JsonData.buildError(list1,-2);
        }

        if (list4.size()>0){
            return JsonData.buildSuccess(list4,-4);
        }

        //list2判断是否存在课题报名
        if (list2.size()>0){
            return JsonData.buildError(list2,-3);
        }

        //申请时间
        String createtime= DateUtil.getCurrentDate();
        //课题申请简要信息
        TaskItemDeclareBasicInfo taskItemDeclareBasicInfo = taskItemDeclareService.getTaskItemDeclareBasicInfoById(taskItemSignUp.getDeclareid());



        for (StudentBasicInfo student:list3){
            TaskItemSignUpBasicInfo taskItemSignUpBasicInfo = new TaskItemSignUpBasicInfo();
            taskItemSignUpBasicInfo.setId(UUIDUtil.getUUID());
            taskItemSignUpBasicInfo.setTaskItemDeclareBasicInfo(taskItemDeclareBasicInfo);
            taskItemSignUpBasicInfo.setStudentBasicInfo(student);
            taskItemSignUpBasicInfo.setDirectorid(majorService.getDirectorIdByMajorName(student.getMajor()));
            taskItemSignUpBasicInfo.setDetail(taskItemSignUp.getDetail());
            taskItemSignUpBasicInfo.setCreatetime(createtime);
            if (student.getId().equals(user.getId())){
                taskItemSignUpBasicInfo.setIsprincipal(1);
            }
            taskItemSignUpService.addTaskItemSignUp(taskItemSignUpBasicInfo);
        }

        return JsonData.buildSuccess("报名成功!");
    }


    //修改课题报名信息
    @RequestMapping("/taskitem/againSignup")
    @ResponseBody
    public JsonData taskItemUpdate(@RequestBody STaskItemSignUp taskItemSignup){
        taskItemSignUpService.deleteTaskItemSignUpByDeclareid(taskItemSignup.getDeclareid());
        return taskItemSignUp(taskItemSignup);
    }



    //查看报名页面
    @RequestMapping("/taskitem/signup/check/page")
    public String taskItemSignupCheckPage(){
        return "student-taskitem-signup-check";
    }

    //查看报名信息
    @RequestMapping("/taskitem/signup/check")
    @ResponseBody
    public JsonData taskItemSignupCheck(){


        User user = (User) session.getAttribute("user");
        TaskItemSignUpBasicInfo taskItemSignUpBasicInfo=taskItemSignUpService.getTaskItemSignUpByStudentid(user.getId());

        if (taskItemSignUpBasicInfo==null){
            return JsonData.buildError("");
        }

        String declareid=taskItemSignUpBasicInfo.getTaskItemDeclareBasicInfo().getId();

        List<String> studentids=taskItemSignUpService.getStudentIdsByDeclareId(declareid,user.getId());
        List<StudentBasicInfo> members=new ArrayList<>();

        if (studentids!=null){
            for (String studentid:studentids){
                StudentBasicInfo studentBasicInfo=studentService.getStudentBasicInfoById(studentid);
                members.add(studentBasicInfo);
            }
        }

        List<Object> list=new ArrayList<>();
        list.add(taskItemSignUpBasicInfo);
        list.add(members);

        return JsonData.buildSuccess(list,0);
    }
}
