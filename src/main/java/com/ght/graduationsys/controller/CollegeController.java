package com.ght.graduationsys.controller;

import com.ght.graduationsys.entity.College;
import com.ght.graduationsys.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @RequestMapping("/list")
    public List<College> collegeList(){
        return collegeService.getAllColleges();
    }
}
