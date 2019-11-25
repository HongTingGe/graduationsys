package com.ght.graduationsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentChatController {
    @RequestMapping("/chat/page")
    public String studentChatPage(){
        return "student-chat";
    }
}
