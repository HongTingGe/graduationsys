package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.Notice;
import com.ght.graduationsys.entity.NoticeJsonData;
import com.ght.graduationsys.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;


    //发布公告页面
    @RequestMapping("/view/notice/add")
    public String addNoticePage(){
        return "add-notice";
    }

    //发布公告
    @RequestMapping("/notice/add")
    @ResponseBody
    public void addNotice(Notice notice){

        List<String> files= (List<String>) session.getAttribute("files");

        if (files!=null){

            String regex="<img[\\s\\S]*?style[\\s\\S]*?>";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(notice.getContent());
            while(matcher.find()) {
                for (String file:files){
                    if (matcher.group().contains(file)){
                        try{
                            //构建上传目标路径,
                            File destFile=new File
                                    (ResourceUtils.getURL("classpath:").getPath());
                            if(!destFile.exists()){
                                destFile=new File("");
                            }

                            File upload=new File(destFile.getAbsolutePath()+"/static/NoticeUpload/",file);

                            //若目标文件夹不存在，则创建一个
                            if (!upload.getParentFile().exists()) {
                                upload.getParentFile().mkdirs();
                            }

                            InputStream input = new FileInputStream(destFile.getAbsolutePath()+"/static/tmpNoticeUpload/"+file);
                            FileOutputStream output = new FileOutputStream(upload.getAbsolutePath());
                            FileCopyUtils.copy(input, output);


                        } catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
        notice.setCreatetime(new Date());
        noticeService.addNotice(notice);
        session.removeAttribute("files");
    }

    @RequestMapping("/notice/update")
    @ResponseBody
    public void updateNoticeById(int id){
        noticeService.updateNoticeById(id);
    }

    @RequestMapping("/notice/delete")
    @ResponseBody
    public void deleteNoticeById(int id){
        noticeService.deleteNoticeById(id);
    }

    @RequestMapping("/notice/getAll")
    @ResponseBody
    public List<Notice> getNoticesByTid(String tid){
        return noticeService.getNoticesByTid(tid);
    }

    @RequestMapping("/notice/get")
    @ResponseBody
    public Notice getNoticeById(int id){
        return noticeService.getNoticeById(id);
    }


    /**
     * 富文本编辑器上传图片(临时)
     * @param file
     * @return
     */
    @RequestMapping("/admin/tmpNoticeUpload")
    @ResponseBody
    public NoticeJsonData upload(@RequestParam("image") MultipartFile file){

        HttpSession session=request.getSession();
        List<String>files= (List<String>) session.getAttribute("files");
        if (files==null){
            files=new ArrayList<String>();
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName=file.getOriginalFilename();
        String suffixName=fileName.substring(fileName.lastIndexOf("."));
        fileName=sdf.format(new Date())+suffixName;



        try {
            //构建上传目标路径,
            File destFile=new File
                    (ResourceUtils.getURL("classpath:").getPath());
            if(!destFile.exists()){
                destFile=new File("");
            }

            File upload=new File(destFile.getAbsolutePath(),"static/tmpNoticeUpload/");

            //若目标文件夹不存在，则创建一个
            if(!upload.exists()){
                upload.mkdir();
            }

            //根据file的大小，准备一个字节数组
            byte[] bytes = file.getBytes();
            //拼接上传路径
            Path path = Paths.get(upload.getAbsolutePath()+"/"+fileName);
            //最重要的一步，将源文件写入目标地址
            Files.write(path , bytes);

            files.add(fileName);
            session.setAttribute("files",files);


            String []data={"/static/tmpNoticeUpload/"+fileName};


            return NoticeJsonData.buildSuccess(data);


        }catch (Exception e){
            e.printStackTrace();
        }
        return NoticeJsonData.buildError(null);
    }

    /**
     * 获取所有公告
     * @return
     */
    @RequestMapping("/getAllNotices")
    @ResponseBody
    public List<Notice> getAllNotices(){
        return noticeService.getAllNotices();
    }


}
