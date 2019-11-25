package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.JsonData;
import com.ght.graduationsys.entity.Report;
import com.ght.graduationsys.entity.ReportJsonData;
import com.ght.graduationsys.entity.User;
import com.ght.graduationsys.service.ReportService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private HttpSession session;

    @RequestMapping("/report/check")
    public String teacherReporChecktPage(){
        return "teacher-report-check";
    }

    @RequestMapping("/get/Reports")
    @ResponseBody
    public ReportJsonData getReportsByTid(int page,int limit){
        User user= (User) session.getAttribute("user");
        Page<Report> pageInfo=PageHelper.startPage(page,limit);
        List<Report> reportList=reportService.getReportsByTid(user.getId());
        return new ReportJsonData(pageInfo.getTotal(),reportList);
    }

    @RequestMapping("/get/Checked/Reports")
    @ResponseBody
    public ReportJsonData getCheckedReportsByTid(int page,int limit){
        User user= (User) session.getAttribute("user");
        Page<Report> pageInfo=PageHelper.startPage(page,limit);
        List<Report> reportList=reportService.getCheckedReportsByTid(user.getId());
        return new ReportJsonData(pageInfo.getTotal(),reportList);
    }

    @RequestMapping("/get/Unchecked/Reports")
    @ResponseBody
    public ReportJsonData getUncheckedReportsByTid(int page,int limit){
        User user= (User) session.getAttribute("user");
        Page<Report> pageInfo=PageHelper.startPage(page,limit);
        List<Report> reportList=reportService.getUncheckedReportsByTid(user.getId());
        return new ReportJsonData(pageInfo.getTotal(),reportList);
    }

    @RequestMapping("/update/report")
    @ResponseBody
    public void updateReportById(String id,String msg){
        reportService.updateReportById(id,msg);
    }
}
