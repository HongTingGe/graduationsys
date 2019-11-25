package com.ght.graduationsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
    @RequestMapping("/member-save")
    public String memberSave(){
        return "member-save";
    }

    @RequestMapping("/member-edit")
    public String memberEdit(){
        return "member-edit";
    }
}
