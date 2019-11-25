package com.ght.graduationsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserInfoController {

    @RequestMapping("/userinfo/check")
    public String userinfoPage(){
        return "userinfo";
    }
}
