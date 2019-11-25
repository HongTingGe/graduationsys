package com.ght.graduationsys.entity;

import lombok.Data;

import java.util.List;

@Data
public class STaskItemSignUp {
    private String id;
    private String declareid;
    private List<StudentBasicInfo> members;
    private String detail;
}
