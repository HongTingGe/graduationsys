package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.*;
import com.ght.graduationsys.service.ReportService;
import com.ght.graduationsys.service.TaskItemSignUpService;
import com.ght.graduationsys.util.DateUtil;
import com.ght.graduationsys.util.PdfUtil;
import com.ght.graduationsys.util.UUIDUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Controller
@RequestMapping("/student")
public class StudentReportController {

    @Autowired
    private HttpSession session;

    @Autowired
    private TaskItemSignUpService taskItemSignUpService;

    @Autowired
    private ReportService reportService;


    //报告查看页面
    @RequestMapping("/report/check")
    public String studentReporChecktPage(){
        return "student-report-check";
    }

    //报告上传页面
    @RequestMapping("/report/upload")
    public String studentReportUploadPage(){
        return "student-report-upload";
    }

    //报告上传
    @RequestMapping("/reports/upload")
    @ResponseBody
    public JsonData studentReportsUpload(MultipartFile[] files){

        User user= (User) session.getAttribute("user");
        TaskItemSignUpBasicInfo signUpBasicInfo = taskItemSignUpService.getTaskItemSignUpByStudentid(user.getId());
        if (signUpBasicInfo == null){
            return JsonData.buildError("您暂未课题报名!不能进行报告上传!");
        }else{
            if (signUpBasicInfo.getTeacherBasicInfo() == null){
                return JsonData.buildError("您的课题报名待审批中,暂未分配指导老师!");
            }
        }


        try {
            //构建上传目标路径,
            File destFile=new File
                    (ResourceUtils.getURL("classpath:").getPath());
            if(!destFile.exists()){
                destFile=new File("");
            }
            //出处目标文件的绝对路径
            //System.out.println("file path"+destFile.getAbsolutePath());
            File upload=new File(destFile.getAbsolutePath(),"static/studentreports/");

            for (MultipartFile file : files){


                String originalFilename= file.getOriginalFilename();//全文件名
                String suffixName=originalFilename.substring(originalFilename.lastIndexOf("."));//后缀名

                String uuid=UUIDUtil.getUUID();
                String fileName= uuid + originalFilename;//生成不重复覆盖的全文件名
                String originalName=uuid+originalFilename.substring(0,originalFilename.lastIndexOf("."));//生成不重复覆盖的全文件名(去除后缀)

                //若目标文件夹不存在，则创建一个
                if(!upload.exists()){
                    upload.mkdir();
                }

                //System.out.println("完整的上传路径:"+upload.getAbsolutePath()+"/"+fileName);

                //根据file的大小，准备一个字节数组
                byte[] bytes = file.getBytes();
                //拼接上传路径
                Path path = Paths.get(upload.getAbsolutePath()+"/"+fileName);
                //最重要的一步，将源文件写入目标地址
                Files.write(path , bytes);
                Report report=new Report();
                if (suffixName.equals(".xls")||suffixName.equals(".xlsx")){
                    PdfUtil.excel2pdf(upload.getAbsolutePath()+"/"+fileName,upload.getAbsolutePath()+"/"+originalName+".pdf");
                    report.setPreviewpath("/static/studentreports/"+originalName+".pdf");
                    report.setRealpath(upload.getAbsolutePath()+"/"+fileName);
                }else{

                    PdfUtil.doc2pdf(upload.getAbsolutePath()+"/"+fileName,upload.getAbsolutePath()+"/"+originalName+".pdf");

                    report.setPreviewpath("/static/studentreports/"+originalName+".pdf");
                    report.setRealpath(upload.getAbsolutePath()+"/"+fileName);
                }




                report.setId(UUIDUtil.getUUID());
                report.setName(originalFilename);

                report.setStudentid(user.getId());
                report.setTeacherid(signUpBasicInfo.getTeacherBasicInfo().getId());
                report.setCreatetime(DateUtil.getCurrentDate());
                reportService.addReport(report);
            }
            return JsonData.buildSuccess();


        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonData.buildError("上传失败!请重新上传!");


    }

    @RequestMapping("/preview")
    public String pdfPage(){
        return "preview";
    }

    @RequestMapping("/get/Reports")
    @ResponseBody
    public ReportJsonData getReportsBySid(int page, int limit){
        User user= (User) session.getAttribute("user");
        Page<Report> pageInfo= PageHelper.startPage(page,limit);
        List<Report> reportList=reportService.getReportsBySid(user.getId());
        return new ReportJsonData(pageInfo.getTotal(),reportList);
    }

    @RequestMapping("/get/Checked/Reports")
    @ResponseBody
    public ReportJsonData getCheckedReportsBySid(int page,int limit){
        User user= (User) session.getAttribute("user");
        Page<Report> pageInfo=PageHelper.startPage(page,limit);
        List<Report> reportList=reportService.getCheckedReportsBySid(user.getId());
        return new ReportJsonData(pageInfo.getTotal(),reportList);
    }

    @RequestMapping("/get/Unchecked/Reports")
    @ResponseBody
    public ReportJsonData getUncheckedReportsBySid(int page,int limit){
        User user= (User) session.getAttribute("user");
        Page<Report> pageInfo=PageHelper.startPage(page,limit);
        List<Report> reportList=reportService.getUncheckedReportsBySid(user.getId());
        return new ReportJsonData(pageInfo.getTotal(),reportList);
    }

}
