package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.*;
import com.ght.graduationsys.service.WorkFileService;
import com.ght.graduationsys.util.DateUtil;
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
public class WorkFileController {

    @Autowired
    private HttpSession session;

    @Autowired
    private WorkFileService workFileService;

    //必要工作文件上传页面
    @RequestMapping("/director/workfile/upload")
    public String workFilePage(){
        return "workfile-upload";
    }

    //必要工作文件上传处理
    @RequestMapping("/director/workfiles/upload")
    @ResponseBody
    public JsonData workFilesUpload(MultipartFile[] files){
        User user= (User) session.getAttribute("user");
        try {
            //构建上传目标路径,
            File destFile=new File
                    (ResourceUtils.getURL("classpath:").getPath());
            if(!destFile.exists()){
                destFile=new File("");
            }
            //出处目标文件的绝对路径
            //System.out.println("file path"+destFile.getAbsolutePath());
            File upload=new File(destFile.getAbsolutePath(),"static/workfiles/");

            for (MultipartFile file : files){


                String originalFilename= file.getOriginalFilename();

                String uuid= UUIDUtil.getUUID();

                String originalName=uuid+originalFilename;
                //若目标文件夹不存在，则创建一个
                if(!upload.exists()){
                    upload.mkdir();
                }

                //System.out.println("完整的上传路径:"+upload.getAbsolutePath()+"/"+fileName);

                //根据file的大小，准备一个字节数组
                byte[] bytes = file.getBytes();
                //拼接上传路径
                Path path = Paths.get(upload.getAbsolutePath()+"/"+originalName);
                //最重要的一步，将源文件写入目标地址
                Files.write(path , bytes);


                WorkFile workFile=new WorkFile();
                workFile.setId(UUIDUtil.getUUID());
                workFile.setName(originalFilename);
                workFile.setMajorid(user.getMajor().getId());
                workFile.setCreatetime(DateUtil.getCurrentDate());
                workFile.setPath("/static/workfiles/"+originalName);
                workFileService.addWorkFile(workFile);

            }
            return JsonData.buildSuccess();


        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonData.buildError("上传失败!请重新上传!");
    }

    //必要工作文件下载页面
    @RequestMapping("/pub/workfile/download")
    public String workFilesDownloadPage(){
        return "workfile-download";
    }

    //获得所有必要工作文件对象
    @RequestMapping("/get/workfiles")
    @ResponseBody
    public WorkFileJsonData getWorkFilesByTid(int page,int limit){
        User user= (User) session.getAttribute("user");
        int majorId=user.getMajor().getId();
        Page<WorkFile> pageInfo= PageHelper.startPage(page,limit);
        List<WorkFile> list=workFileService.getWorkFilesByMajorId(majorId);
        return new WorkFileJsonData(pageInfo.getTotal(),list);
    }
}
