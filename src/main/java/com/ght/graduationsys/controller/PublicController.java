package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.*;
import com.ght.graduationsys.service.PermissionModuleService;
import com.ght.graduationsys.util.MenuUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/pub")
public class PublicController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private PermissionModuleService permissionModuleService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/checklogin")
    @ResponseBody
    public JsonData index(String id,String password,String type){

        request.getSession().setAttribute("type",type);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>();
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(id,password);

            subject.login(usernamePasswordToken);

            info.put("msg","登录成功");
            //判断登录类型
            //System.out.println(SecurityUtils.getSubject().getPrincipal() instanceof Teacher);
            List<Role> roleList=null;
            if (SecurityUtils.getSubject().getPrincipal() instanceof Student){
                roleList=((Student) SecurityUtils.getSubject().getPrincipal()).getRoleList();
            }else{
                roleList=((Teacher) SecurityUtils.getSubject().getPrincipal()).getRoleList();
            }
            LinkedHashMap<PermissionModule,List<Permission>> menus = MenuUtil.getMenus(permissionModuleService,roleList);

            User user= (User) SecurityUtils.getSubject().getPrincipal();

            session.setAttribute("user",user);



            return JsonData.buildSuccess(user,info,menus);

        }catch (Exception e){
            e.printStackTrace();

            return JsonData.buildError("账号或者密码错误");

        }
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }


    @RequestMapping("/not_permit")
    public String notPermit(){
        return "not_permit";
    }


    @RequestMapping("/notice/check/page")
    public String showNoticesPage(){
        return "show-notice";
    }
}
