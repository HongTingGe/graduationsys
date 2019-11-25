package com.ght.graduationsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/manage/teacher/page")
    public String adminManageTeacherPage(){
        return "admin-manage-teacher";
    }
}
