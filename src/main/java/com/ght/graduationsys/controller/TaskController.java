package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.JsonData;
import com.ght.graduationsys.entity.Task;
import com.ght.graduationsys.entity.TaskJsonData;
import com.ght.graduationsys.entity.User;
import com.ght.graduationsys.service.MajorService;
import com.ght.graduationsys.service.TaskItemDeclareService;
import com.ght.graduationsys.service.TaskService;
import com.ght.graduationsys.util.ExcelUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskItemDeclareService taskItemDeclareService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private HttpSession session;

    @RequestMapping("/list")
    public List<Task> taskList(){
        return taskService.getAllTasks();
    }

    @RequestMapping("/list/bymarjorid")
    public List<Task> taskListByMajorid(){
        User user= (User) session.getAttribute("user");
        return taskService.getTasksByMajorId(user.getMajor().getId());
    }


    @RequestMapping("/list/marjorid/info")
    public Map taskListByMajoridInfo(){
        User user= (User) session.getAttribute("user");
        String type = (String) session.getAttribute("type");
        List<Task> taskList1 = taskService.getTasksByMajorId(user.getMajor().getId());
        List<Task> taskList2 = null;
        if (type.equals("student")){
            int majorId = user.getMajor().getId();
            String directorId = majorService.getDirectorIdById(majorId);
            taskList2 = taskItemDeclareService.getTasksByDirectorId(directorId);
        } else{
            taskList2 = taskItemDeclareService.getTasksByDirectorId(user.getId());
        }
        Map<String,Integer> map = new LinkedHashMap<>();
        for (Task task : taskList1){
            map.put(task.getName(),0);
        }
        for (Task task : taskList2){
            if (map.containsKey(task.getName())){
                Integer count = map.get(task.getName());
                count++;
                map.put(task.getName(),count);
            }
        }
        return map;
    }



    //分页
    @RequestMapping("/all")
    public TaskJsonData getAllTasks(int page,int limit){
        User user=(User)session.getAttribute("user");
        int majorid=user.getMajor().getId();
        Page<Task> pageInfo= PageHelper.startPage(page,limit);
        List<Task> tasks=taskService.getTasksByMajorId(majorid);
        return new TaskJsonData(pageInfo.getTotal(),tasks);
    }

    @RequestMapping("/update")
    public JsonData updateTaskById(Task task){
        taskService.updateTaskById(task);
        return JsonData.buildSuccess("更新成功!");
    }

    @RequestMapping("/delete")
    public void updateTaskById(int id){
        taskService.deleteTaskById(id);
    }

    @RequestMapping("/upload")
    @ResponseBody
    public JsonData uploadTaskTypeFile(MultipartFile file){
        User user= (User) session.getAttribute("user");
        JsonData jsonData = ExcelUtil.importExcel(file,Task.class);
        if (jsonData.getData()!=null){
            List<Task> tasks=(List<Task>)jsonData.getData();
            for (Task task:tasks){
                task.setMajorid(user.getMajor().getId());
                taskService.addTask(task);
            }
        }
        return jsonData;


    }

    @RequestMapping("/download")
    public void downloadTaskTemplateFile(HttpServletResponse response){


        try {

            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("课题方向","UTF-8") + ".xls");

            ExcelUtil.downTemplateFile(response.getOutputStream());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
